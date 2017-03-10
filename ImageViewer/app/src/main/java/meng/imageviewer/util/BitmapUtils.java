package meng.imageviewer.util;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by meng on 2017/3/9.
 */

public class BitmapUtils {

    public static Bitmap scaleBitmap(Bitmap src, int maxWidth, int maxHeight, boolean recycle) {
        if (src == null || src.isRecycled()) {
            return src;
        }
        if (src.getWidth() < maxWidth && src.getHeight() < maxHeight) {
            return src;
        }
        Point size = caculateSize(new Point(src.getWidth(), src.getHeight()), new Point(maxWidth,
                maxHeight), false);

        Bitmap bitmap = Bitmap.createScaledBitmap(src, size.x, size.y, true);
        if (bitmap != src && recycle) {
            src.recycle();
        }
        return bitmap;
    }

    public static Point caculateSize(Point src, Point limit, boolean extendToLimit) {
        int maxWidth = limit.x;
        int maxHeight = limit.y;
        int w = Math.max(1, src.x);
        int h = Math.max(1, src.y);
        if (maxWidth == 0 && maxHeight == 0) {
            // 不做任何限制
        } else if (maxWidth == 0) {
            if (h > maxHeight) {
                w = w * maxHeight / h;
                h = maxHeight;
            }
        } else if (maxHeight == 0) {
            if (w > maxWidth) {
                h = h * maxWidth / w;
                w = maxWidth;
            }
        } else {
            if (w * maxHeight > h * maxWidth && (extendToLimit || w > maxWidth)) {
                h = h * maxWidth / w;
                w = maxWidth;
            } else if (w * maxHeight < h * maxWidth && (extendToLimit || h > maxHeight)) {
                w = w * maxHeight / h;
                h = maxHeight;
            }
        }
        return new Point(w, h);
    }
}
