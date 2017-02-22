package meng.statusbartint.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn_splash).setOnClickListener(this);
        findViewById(R.id.btn_red).setOnClickListener(this);
        findViewById(R.id.btn_white).setOnClickListener(this);
        findViewById(R.id.btn_solid_status_bar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_splash) {
            startActivity(new Intent(this, SplashActivity.class));
        } else if (v.getId() == R.id.btn_red) {
            startActivity(new Intent(this, RedActivity.class));
        } else if (v.getId() == R.id.btn_white) {
            startActivity(new Intent(this, WhiteActivity.class));
        } else if (v.getId() == R.id.btn_solid_status_bar) {
            Intent intent = new Intent(this, SolidStatusBarActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_omni_status_bar) {
            startActivity(new Intent(this, OmniThemeActivity.class));
        } else if (v.getId() == R.id.btn_omni_status_bar_with_head) {
            startActivity(new Intent(this, OmniThemeWithHeadActivity.class));
        }
    }
}
