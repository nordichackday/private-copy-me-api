package nordichack.pcmapi.rest;


import nordichack.pcmapi.model.VideoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class DownloadController {

    @Autowired
    private DownloadRestService downloadRestService;


    @RequestMapping(method =  RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam(required = true, value = "url") String url,  @RequestHeader HttpHeaders headers) {


        VideoFile videofile  = new VideoFile( url);
        final byte[] bytes = downloadRestService.downloadVideo(videofile, headers);

        final HttpHeaders headersOut = new HttpHeaders();
        headersOut.setContentType(MediaType.parseMediaType("video/mp4"));
        headersOut.set("Content-Disposition", "attachment; filename=\""+videofile.getFilename()+"\"");

        return new ResponseEntity<>(bytes, headersOut, HttpStatus.OK);

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal argument in request, verify json and app-parameter")
    @ExceptionHandler(IllegalArgumentException.class)
    public void exceptionHandler()
    {}
}
