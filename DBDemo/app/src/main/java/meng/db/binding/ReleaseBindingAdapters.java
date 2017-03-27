package meng.db.binding;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import meng.db.utils.FontCache;

/**
 * Created by meng on 2017/3/4.
 */
public class ReleaseBindingAdapters extends BindingAdapters {

    @Override
    public void setText(TextView textView, String text) {
        android.databinding.adapters.TextViewBindingAdapter.setText(textView, text);
        textView.setTextColor(Color.RED);
    }

    @Override
    public void setTextSize(TextView textView, float textSize) {
        android.databinding.adapters.TextViewBindingAdapter.setTextSize(textView, textSize);
    }

    @Override
    public void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getInstance(
                textView.getContext()).get(fontName));
    }

    @Override
    public void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @Override
    public void setImageUrl(ImageView imageView, String url, Drawable placeHolder) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeHolder)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
