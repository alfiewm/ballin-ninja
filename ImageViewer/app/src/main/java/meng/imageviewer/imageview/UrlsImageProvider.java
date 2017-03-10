package meng.imageviewer.imageview;

import android.os.Handler;
import android.os.Looper;

import meng.imageviewer.data.ImageUrls;

/**
 * Created by meng on 2017/3/9.
 */
class UrlsImageProvider implements ImageProvider {

    private final int defaultPos;
    private final boolean allowDownload;
    private String[] data;

    UrlsImageProvider(String[] data, int defaultPos, boolean allowDownload) {
        this.data = data;
        this.defaultPos = defaultPos;
        this.allowDownload = allowDownload;
    }

    public void setData(String[] urls) {
        this.data = urls;
        if (dataSetChangeListener != null) {
            dataSetChangeListener.notifyDataSetChanged(2);
        }
    }

    @Override
    public void init() {
        // prefetch data or sth
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                setData(ImageUrls.urls5);
            }
        }, 5000);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.length;
    }

    @Override
    public int getDefaultPosition() {
        return defaultPos;
    }

    @Override
    public boolean allowDownload() {
        return allowDownload;
    }

    @Override
    public ImageThumbnail getThumbnail(int index) {
        return new ImageThumbnail(
                "file:///storage/emulated/0/Pictures/Tumblr/IMG_~0" + 4 + ".jpg", null);
    }

    @Override
    public ImageSource getImageSource(int index) {
        if (data[index].equalsIgnoreCase("https://unsplash.it/900?image=1")) {
            // forge a error url
            return new ImageSource("http://www.baidu.com/", "");
        } else {
            return new ImageSource(data[index], "");
        }
    }

    private transient DataSetChangeListener dataSetChangeListener;

    @Override
    public void setDataSetChangeListener(DataSetChangeListener dataSetChangeListener) {
        this.dataSetChangeListener = dataSetChangeListener;
    }
}
