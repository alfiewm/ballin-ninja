package meng.statusbartint.ui;

import static meng.statusbartint.R.id.back_btn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;
import meng.statusbartint.util.StatusBarUtil;
import meng.statusbartint.util.SystemStatusBarHelper;

public class SolidStatusBarActivity extends BaseActivity implements View.OnClickListener {

    private boolean lightStatusBar;
    private static int[] solid_colors = {Color.WHITE, Color.RED, Color.parseColor("#008000"), Color.BLUE};
    private int index;
    private ImageView backBtn;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_status_bar);
        backBtn = ((ImageView) findViewById(back_btn));
        titleView = ((TextView) findViewById(R.id.title));
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(
                STATUS_BAR_COLOR)) {
            int color = intent.getExtras().getInt(STATUS_BAR_COLOR, Color.BLUE);
            switchNavBarAndStatusBarBg(color);
        }
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == back_btn) {
            finish();
        } else if (v.getId() == R.id.switch_status_bar_icon) {
            setLightStatusBar(!lightStatusBar);
        } else if (v.getId() == R.id.switch_status_bar_color) {
            int color = solid_colors[(index++) % solid_colors.length];
            switchNavBarAndStatusBarBg(color);
        }
    }

    private void switchNavBarAndStatusBarBg(int color) {
        SystemStatusBarHelper.setStatusBarColor(this, color);
        if (color == Color.WHITE) {
            backBtn.setImageResource(R.drawable.arrow_back_black);
            titleView.setTextColor(Color.BLACK);
            setLightStatusBar(true);
        } else {
            backBtn.setImageResource(R.mipmap.ic_back);
            titleView.setTextColor(Color.WHITE);
            setLightStatusBar(false);
        }
        findViewById(R.id.navbar).setBackgroundColor(color);
    }

    private void setLightStatusBar(boolean light) {
        lightStatusBar = light;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (light) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back
                // completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }
//        setMIUIStatusBar(light);
        StatusBarUtil.MIUISetStatusBarLightMode(getWindow(), light);
        StatusBarUtil.FlymeSetStatusBarLightMode(getWindow(), light);
    }

    private void setMIUIStatusBar(boolean darkStatusBarIconAndText) {
        Window window = getWindow();

        Class clazz = window.getClass();
        try {
            int tranceFlag = 0;
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");

            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);

            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);

            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);

            //只需要状态栏透明
//            extraFlagField.invoke(window, tranceFlag, tranceFlag); // 或
            if (darkStatusBarIconAndText) {
                // 状态栏透明且黑色字体
                extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag);
            } else {
                //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
