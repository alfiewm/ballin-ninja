package meng.statusbartint.ui;

import android.os.Bundle;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

public class WhiteActivity extends BaseActivity {

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_white;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white);
    }
}
