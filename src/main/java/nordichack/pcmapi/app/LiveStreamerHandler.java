package nordichack.pcmapi.app;

import nordichack.pcmapi.model.LiveStreamer;

import nordichack.pcmapi.model.VideoFile;
import org.springframework.beans.factory.annotation.Autowired;

public class LiveStreamerHandler {

    @Autowired
    LiveStreamer liveStreamer;

    public byte[] toMP4(VideoFile videofile) {
         liveStreamer.toFile(videofile);
        return liveStreamer.getFile(videofile);
    }
}
