package meng.db.binding;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import meng.db.utils.FontCache;

/**
 * Created by meng on 2017/3/27.
 */
public class DebugBindingAdapter extends BindingAdapters {

    @Override
    public void setText(TextView textView, String text) {
        android.databinding.adapters.TextViewBindingAdapter.setText(textView, text);
        textView.setTextColor(Color.BLUE);
    }

    @Override
    public void setTextSize(TextView textView, float textSize) {
        android.databinding.adapters.TextViewBindingAdapter.setTextSize(textView, textSize * 2);
    }

    @Override
    public void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.getInstance(textView.getContext()).get(fontName));
    }

    private static final String TEST_IMG_URL = "https://unsplash.it/300/300?random";

    @Override
    public void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(TEST_IMG_URL)
                .into(imageView);
    }

    @Override
    public void setImageUrl(ImageView imageView, String url, Drawable placeHolder) {
        Glide.with(imageView.getContext())
                .load(TEST_IMG_URL)
                .placeholder(placeHolder)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
