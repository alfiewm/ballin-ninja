package meng.statusbartint.ui;

import android.os.Bundle;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
//        StatusBarUtil.transparencyBar(this);
//        StatusBarUtil.setLightStatusBar(this, false);
        setContentView(R.layout.activity_splash);
    }
}
