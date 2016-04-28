package nordichack.pcmapi.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiveStreamer {

    public boolean toFile(String url){
        List<String> commands = new ArrayList<String>();
        commands.add("livestreamer");
       commands.add(url);
      commands.add("best");
         commands.add("-o");
        commands.add(System.getProperty("java.io.tmpdir") + "/video.mp4");


        try {




            ProcessBuilder pb = new ProcessBuilder(commands);

            Process process = pb.start();
            int errCode = process.waitFor();
            System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
            System.out.println("Echo Output:\n" + output(process.getInputStream()));
            return true;
        } catch (Exception e) {
            System.out.println("ERR" + e);

        }
        return false;
    }

    public byte[] getFile() {

        try {
            Path path = Paths.get(System.getProperty("java.io.tmpdir") + "/" + "video.mp4");
            return Files.readAllBytes(path);
        } catch ( Exception e) {
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
