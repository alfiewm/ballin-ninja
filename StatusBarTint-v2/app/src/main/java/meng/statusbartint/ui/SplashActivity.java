package meng.statusbartint.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.eh_img)
    ImageView ehImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        ButterKnife.bind(this);
        ehImageView.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ehImageView.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_splash;
    }
}
