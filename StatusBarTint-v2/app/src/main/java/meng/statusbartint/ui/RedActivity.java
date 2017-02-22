package meng.statusbartint.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class RedActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btn_toast).setOnClickListener(this);
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_red;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_toast) {
            Toast.makeText(this, "Oh, a toast!", Toast.LENGTH_SHORT).show();
        }
    }
}
