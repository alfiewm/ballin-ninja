package meng.imageviewer.imageview;

import android.graphics.Bitmap;

/**
 * Created by meng on 2017/3/8.
 */
public interface ImageLoadListener {
    /**
     * 图片加载成功回调
     */
    void onSuccess(Bitmap bitmap);

    /**
     * 图片加载失败回调
     */
    void onFail(String errorMsg);
}
