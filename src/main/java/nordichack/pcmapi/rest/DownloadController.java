package nordichack.pcmapi.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class DownloadController {

    @Autowired
    private DownloadRestService downloadRestService;


    @RequestMapping(method =  RequestMethod.GET , consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<byte[]> download(@RequestParam(required = true, value = "url") String url,  @RequestHeader HttpHeaders headers) {

        final HttpHeaders headersOut = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        return new ResponseEntity<>(downloadRestService.downloadVideo(url, headers), headersOut, HttpStatus.OK);

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal argument in request, verify json and app-parameter")
    @ExceptionHandler(IllegalArgumentException.class)
    public void exceptionHandler()
    {}
}
