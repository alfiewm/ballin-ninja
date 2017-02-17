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
        setContentView(R.layout.activity_red);
        findViewById(R.id.btn_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_toast) {
            Toast.makeText(this, "Oh, a toast!", Toast.LENGTH_SHORT).show();
        }
    }
}
