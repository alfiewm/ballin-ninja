package meng.imageviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import meng.imageviewer.imageview.DataSetChangeListener;
import meng.imageviewer.imageview.ImageProvider;

public class ImagesViewer extends AppCompatActivity {

    private final static String EXTRA_PROVIDER = "extra_provider";

    public static void launch(Context context, ImageProvider provider) {
        Intent intent = new Intent(context, ImagesViewer.class);
        intent.putExtra(EXTRA_PROVIDER, provider);
        context.startActivity(intent);
    }

    private ViewPager viewPager;
    private ImageProvider imageProvider;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        imageProvider = (ImageProvider) getIntent().getSerializableExtra(EXTRA_PROVIDER);
        if (imageProvider == null) {
            finish();
        }
        imageProvider.init();
        adapter = new ImageAdapter(getSupportFragmentManager(), imageProvider);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(imageProvider.getDefaultPosition());
        imageProvider.setDataSetChangeListener(new DataSetChangeListener() {
            @Override
            public void notifyDataSetChanged(int offset) {
                if (isFinishing()) {
                    return;
                }
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(viewPager.getCurrentItem() + offset, false);
            }
        });
    }

    private static class ImageAdapter extends FragmentStatePagerAdapter {

        private ImageProvider imageProvider;

        public ImageAdapter(FragmentManager fm, ImageProvider imageProvider) {
            super(fm);
            this.imageProvider = imageProvider;
        }

        @Override
        public Fragment getItem(int position) {
            SingleImageFragment fragment = new SingleImageFragment();
            fragment.setParams(imageProvider, position);
            return fragment;
        }

        @Override
        public int getCount() {
            return imageProvider.getCount();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

}
