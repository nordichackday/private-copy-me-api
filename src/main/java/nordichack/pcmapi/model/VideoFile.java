package nordichack.pcmapi.model;


import nordichack.pcmapi.Utils;

import java.util.UUID;

public class VideoFile {

    private String url;
    private String filename;
    private byte[] filecontents;
    private UUID uuid = UUID.randomUUID();


    public VideoFile(String url){
        this.url = url;
        this.filename = Utils.getFilename(url);
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFilecontents() {
        return filecontents;
    }

    public void setFilecontents(byte[] filecontents) {
        this.filecontents = filecontents;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
