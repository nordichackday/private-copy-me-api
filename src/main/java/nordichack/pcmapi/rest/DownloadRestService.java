package nordichack.pcmapi.rest;

import nordichack.pcmapi.app.LiveStreamerHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class DownloadRestService {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private LiveStreamerHandler liveStreamerHandler;

    public byte[] downloadVideo(String url, HttpHeaders httpHeaders) {
        return liveStreamerHandler.toMP4(url);
    }

}
