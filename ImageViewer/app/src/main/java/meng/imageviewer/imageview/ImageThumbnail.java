package meng.imageviewer.imageview;

import android.graphics.Bitmap;

/**
 * Created by meng on 2017/3/9.
 */
public class ImageThumbnail {
    private String localPath;
    private Bitmap bitmap;

    public ImageThumbnail(String localPath, Bitmap bitmap) {
        this.localPath = localPath;
        this.bitmap = bitmap;
    }

    public String getLocalPath() {
        return localPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
