package meng.stickerproto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import java.lang.ref.WeakReference;

/**
 * 用来处理图文混排中输入框高度跳动问题 Created by meng on 2016/12/23.
 */
public class EmojiSpan extends ImageSpan {

    public static final int EMOJI_PADDING = 7;

    public EmojiSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, Paint paint) {
        Drawable b = getCachedDrawable();
        canvas.save();

        int transY = bottom - b.getBounds().bottom;
        if (mVerticalAlignment == ALIGN_BASELINE) {
            transY -= paint.getFontMetricsInt().descent;
        }

        canvas.translate(x + 4, transY + 8);
        b.draw(canvas);
        canvas.restore();
    }

    private WeakReference<Drawable> mDrawableRef;

    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null)
            d = wr.get();

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<>(d);
        }

        return d;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end,
                       Paint.FontMetricsInt fm) {
        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();

/*        if (fm != null) {
            // 重置 FontMetricsInt，避免图文混排高度不一致
            fm.ascent = paint.getFontMetricsInt().ascent;
            fm.descent = paint.getFontMetricsInt().descent;

            fm.top = paint.getFontMetricsInt().top;
            fm.bottom = paint.getFontMetricsInt().bottom;
        }*/

        return rect.right + EMOJI_PADDING;
    }
}
