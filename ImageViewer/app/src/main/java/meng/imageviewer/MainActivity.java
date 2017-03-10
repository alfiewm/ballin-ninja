package meng.imageviewer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import meng.imageviewer.data.ImageUrls;
import meng.imageviewer.databinding.ActivityMainBinding;
import meng.imageviewer.imageview.ImageProviderFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.imagePreview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.imagePreview) {
            ImagesViewer.launch(this,
                    ImageProviderFactory.createUrlsProvider(ImageUrls.urls3, 2, true));
        }
    }
}
