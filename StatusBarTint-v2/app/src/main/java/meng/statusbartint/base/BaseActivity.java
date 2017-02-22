package meng.statusbartint.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
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
        setContentView(R.layout.activity_base);
        ViewStub viewStub = (ViewStub) findViewById(R.id.content);
        viewStub.setLayoutResource(getContentLayoutResId());
        viewStub.inflate();
        View statusPaddingView = findViewById(R.id.base_status_bar_padding_view);
        StatusBarUtil.setLightStatusBarColor(this, statusPaddingView, extendsStatusBar());
    }

    @LayoutRes
    protected abstract int getContentLayoutResId();

    /**
     * 内容是否需要延伸至状态栏下面
     */
    protected boolean extendsStatusBar() {
        return false;
    }
}
