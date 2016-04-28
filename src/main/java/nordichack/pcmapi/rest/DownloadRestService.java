package nordichack.pcmapi.rest;

import nordichack.pcmapi.app.LiveStreamerHandler;

import nordichack.pcmapi.model.VideoFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.IntegerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DownloadRestService {

    private static final Logger LOG = LogManager.getLogger();

    private AtomicInteger progress;

    @Autowired
    private LiveStreamerHandler liveStreamerHandler;

    public byte[] downloadVideo(VideoFile videoFile, HttpHeaders httpHeaders) {
        return liveStreamerHandler.toMP4(videoFile);
     }
}
