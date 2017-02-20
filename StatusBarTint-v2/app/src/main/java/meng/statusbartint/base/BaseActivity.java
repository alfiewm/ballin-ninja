package meng.statusbartint.base;

import android.app.Activity;
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
        SystemStatusBarHelper.setStatusBarColor(this);
    }
}
