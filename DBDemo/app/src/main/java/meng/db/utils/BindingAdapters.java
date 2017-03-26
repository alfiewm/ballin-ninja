package meng.db.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.adapters.TextViewBindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by meng on 2017/3/4.
 */
@BindingMethods({
        @BindingMethod(type = View.class, attribute = "android:onClick", method = "setOnClickListener"),
        @BindingMethod(type = View.class, attribute = "android:backgroundTint", method = "setBackgroundTintList"),
})
public class BindingAdapters {

    @BindingAdapter({"android:text"})
    public static void setText(TextView textView, String text) {
        TextViewBindingAdapter.setText(textView, text);
        textView.setTextColor(Color.BLUE);
    }

    @BindingAdapter({"font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getInstance(
                textView.getContext()).get(fontName));
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @BindingAdapter({"imageUrl", "placeHolder"})
    public static void setImageUrl(ImageView imageView, String url, Drawable placeHolder) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeHolder)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
