package nordichack.pcmapi.app;

import nordichack.pcmapi.model.LiveStreamer;

import org.springframework.beans.factory.annotation.Autowired;

public class LiveStreamerHandler {

    @Autowired
    LiveStreamer liveStreamer;

    public byte[] toMP4(String url) {
        liveStreamer.toFile(url);
        return liveStreamer.getFile();
    }
}
