package nordichack.pcmapi;

import nordichack.pcmapi.model.VideoFile;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String getFilename(String url){
         return Paths.get(url).getFileName().toString().replace("#","").concat(".mp4");
    }

    public static void deleteFile(Path path){
        if(Files.exists(path)){
          try {
              Files.delete(path);
          } catch (Exception e){

          }

        }
    }

}
