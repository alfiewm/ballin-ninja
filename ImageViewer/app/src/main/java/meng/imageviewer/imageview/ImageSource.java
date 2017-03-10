package meng.imageviewer.imageview;

/**
 * Created by meng on 2017/3/9.
 */

public class ImageSource {
    private String remotePath;
    private String localPath;

    public ImageSource(String remotePath, String localPath) {
        this.remotePath = remotePath;
        this.localPath = localPath;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public String getLocalPath() {
        return localPath;
    }
}
