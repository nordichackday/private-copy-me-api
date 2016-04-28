package nordichack.pcmapi.model;

import nordichack.pcmapi.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LiveStreamer {

    public void toFile(VideoFile videoFile) {



        List<String> commands = new ArrayList<String>();
        commands.add("livestreamer");
        commands.add(videoFile.getUrl());
        commands.add("best");
        commands.add("-o");
        commands.add(System.getProperty("java.io.tmpdir") + "/" + videoFile.getFilename() + videoFile.getUuid().toString());

        try {

            ProcessBuilder pb = new ProcessBuilder(commands);
            Process process = pb.start();
            int errCode = process.waitFor();
            System.out.println("Livestreamer command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
            System.out.println("Livestreamer output:\n" + output(process.getInputStream()));
        } catch (Exception e) {
            System.out.println("ERR" + e);

        }
    }

    public byte[] getFile(VideoFile videofile) {

        try {
            Path path = Paths.get(System.getProperty("java.io.tmpdir") + "/" + videofile.getFilename() + videofile.getUuid());
            return Files.readAllBytes(path);
        } catch (Exception e) {
            return null;
        }

    }

    private static String output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }


}
