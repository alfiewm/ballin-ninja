package meng.statusbartint.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class OmniThemeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omni_theme);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.bg_eh:
                Toast.makeText(this, "Clicked bg!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
