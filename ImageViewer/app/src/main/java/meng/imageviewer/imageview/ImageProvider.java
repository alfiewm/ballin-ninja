package meng.imageviewer.imageview;

import java.io.Serializable;

/**
 * Created by meng on 2017/3/8.
 */
public interface ImageProvider extends Serializable {

    void init();

    int getCount();

    int getDefaultPosition();

    boolean allowDownload();

    ImageThumbnail getThumbnail(int index);

    ImageSource getImageSource(int index);

    void setDataSetChangeListener(DataSetChangeListener dataSetChangeListener);
}
