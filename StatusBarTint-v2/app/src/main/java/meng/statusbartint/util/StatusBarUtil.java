package meng.statusbartint.util;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by meng on 2017/2/17.
 */
public class StatusBarUtil {

    /**
     * 设置页面系统状态栏为透明
     */
    public static void setLightStatusBarColor(Activity activity, View statusPaddingView,
            boolean extendsStatusBar) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT
                || activity == null || activity.getWindow() == null) {
            return;
        }
        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams) statusPaddingView.getLayoutParams();
        params.height = StatusBarUtil.getStatusBarHeight(activity);
        statusPaddingView.setLayoutParams(params);
        statusPaddingView.setBackgroundColor(Color.WHITE);
        statusPaddingView.setVisibility(
                extendsStatusBar || Build.VERSION.SDK_INT < LOLLIPOP ? View.GONE : View.VISIBLE);
        // 1. 如果是6.0及以上系统，设置lightstatusbar，并设置浅色背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setLightStatusBar(activity, true);
            setStatusBarColor(activity, Color.TRANSPARENT);
            return;
        }

        // 2. 如果是MIUI v6及以上系统，设置透明深色icon模式
        if (StatusBarUtil.isSupportedMIUI()
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            StatusBarUtil.MIUISetStatusBarLightMode(activity.getWindow(), true);
            return;
        }
        // 3. 如果是Flyme系统，同上尝试设置透明深色icon模式
        if (StatusBarUtil.isSupportedFlyme(activity)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            StatusBarUtil.FlymeSetStatusBarLightMode(activity.getWindow(), true);
            return;
        }
        // 4. 如果是其他系统>=5.0，设置半透明statusbar,
        // 其实4.4以上就可以设置半透明状态栏，但是4.4上面statusbar是渐变效果，搭配白色背景很丑，暂时从5.0开始
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 初始化动态状态栏
     */
    public static void initDynamicStatusBarBg(Activity activity, View statusBarPaddingView) {
        if (Build.VERSION.SDK_INT >= LOLLIPOP || setLightStatusBar(activity, false)) {
            statusBarPaddingView.setVisibility(View.VISIBLE);
        } else {
            statusBarPaddingView.setVisibility(View.GONE);
        }
    }

    /**
     * 修改状态栏颜色，支持6.0以上版本,其实是可以支持5.0及以上版本，但是<6.0时原生系统不能修改状态栏icon颜色， 如果设置浅背景色时，icon就看不见啦
     */
    public static void setStatusBarColor(Activity activity, @ColorInt int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorId);
        }
    }

    /**
     * 设置浅色状态栏模式，状态栏图标和文字将变为深色
     */
    public static boolean setLightStatusBar(Activity activity, boolean light) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = activity.getWindow().getDecorView();
            if (light) {
                decor.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            return true;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return false;
        } else if (isSupportedMIUI()) {
            return MIUISetStatusBarLightMode(activity.getWindow(), light);
        } else if (isSupportedFlyme(activity)) {
            return FlymeSetStatusBarLightMode(activity.getWindow(), light);
        } else {
            return false;
        }
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     */
    private static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     */
    private static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";

    private static boolean isSupportedMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            String versionName = prop.getProperty(KEY_MIUI_VERSION_NAME, null);
            return versionName != null && "V6".compareTo(versionName) <= 0;
        } catch (final IOException e) {
            return false;
        }
    }

    private static boolean isSupportedFlyme(Activity activity) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            return true;
        } catch (final NoSuchFieldException e) {
            return false;
        }
    }

    private static int getStatusBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int result = 72; // 1920x1280
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
