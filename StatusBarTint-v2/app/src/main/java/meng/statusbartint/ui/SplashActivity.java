package meng.statusbartint.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, OmniThemeActivity.class));
                finish();
            }
        }, 2000);
    }
}
