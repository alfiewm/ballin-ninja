package meng.statusbartint.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import meng.statusbartint.util.SystemStatusBarHelper;

/**
 * Created by meng on 2017/2/16.
 */

public class BaseActivity extends Activity {
    public static final String STATUS_BAR_COLOR = "STATUS_BAR_COLOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(
                STATUS_BAR_COLOR)) {
            int color = intent.getExtras().getInt(STATUS_BAR_COLOR, Color.RED);
            SystemStatusBarHelper.setStatusBarColor(this, color);
        }
    }
}
