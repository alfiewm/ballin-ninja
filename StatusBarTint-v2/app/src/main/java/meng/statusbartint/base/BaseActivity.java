package meng.statusbartint.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.ViewStub;

import meng.statusbartint.R;
import meng.statusbartint.util.StatusBarUtil;

/**
 * Created by meng on 2017/2/16.
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (drawBeneathStatusBar()) {
            setContentView(R.layout.activity_base_no_fit_system_window);
        } else {
            setContentView(R.layout.activity_base);
        }
        ViewStub viewStub = (ViewStub) findViewById(R.id.content);
        viewStub.setLayoutResource(getContentLayoutResId());
        viewStub.inflate();
        int statusBarColor = StatusBarUtil.supportLightStatusBar() ? Color.WHITE : Color.GRAY;
        StatusBarUtil.initStatusBar(this, drawBeneathStatusBar(), statusBarColor,
                StatusBarUtil.MODE_LIGHT);
    }

    @LayoutRes
    protected abstract int getContentLayoutResId();

    /**
     * 内容是否需要延伸至状态栏下面
     */
    protected boolean drawBeneathStatusBar() {
        return false;
    }
}
