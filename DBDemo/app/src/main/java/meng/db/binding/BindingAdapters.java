package meng.db.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by meng on 2017/3/27.
 */
public abstract class BindingAdapters {
    @BindingAdapter({"android:text"})
    public abstract void setText(TextView textView, String text);

    @BindingAdapter({"android:textSize"})
    public abstract void setTextSize(TextView textView, float textSize);

    @BindingAdapter({"font"})
    public abstract void setFont(TextView textView, String fontName);

    @BindingAdapter({"imageUrl"})
    public abstract void setImageUrl(ImageView imageView, String url);

    @BindingAdapter({"imageUrl", "placeHolder"})
    public abstract void setImageUrl(ImageView imageView, String url, Drawable placeHolder);
}
