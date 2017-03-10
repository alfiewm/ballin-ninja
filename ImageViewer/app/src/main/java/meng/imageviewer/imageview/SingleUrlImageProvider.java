package meng.imageviewer.imageview;

/**
 * Created by meng on 2017/3/10.
 */

class SingleUrlImageProvider implements ImageProvider {
    private String url;
    private boolean allowDownload;

    SingleUrlImageProvider(String url, boolean allowDownload) {
        this.url = url;
        this.allowDownload = allowDownload;
    }

    @Override
    public void init() {
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public int getDefaultPosition() {
        return 0;
    }

    @Override
    public boolean allowDownload() {
        return allowDownload;
    }

    @Override
    public ImageThumbnail getThumbnail(int index) {
        return null;
    }

    @Override
    public ImageSource getImageSource(int index) {
        return new ImageSource(url, "");
    }

    @Override
    public void setDataSetChangeListener(DataSetChangeListener dataSetChangeListener) {
    }
}
