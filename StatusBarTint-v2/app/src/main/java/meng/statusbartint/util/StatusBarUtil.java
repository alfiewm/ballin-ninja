package meng.statusbartint.util;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by meng on 2017/2/17.
 */
public class StatusBarUtil {

    /**
     * 初始化系统状态栏，将系统状态栏设置为透明色
     * 在根View上方添加一个paddingView，来充当状态栏背景
     * （这么做的原因一是为了在各个版本之间保持统一，二是为了实现动态状态栏颜色)
     */
    public static void initTransparentStatusBar(Activity activity, View statusBarPaddingView,
            boolean drawBeneathStatusBar, @ColorInt int statusBarBackgroundColor) {
        if (activity == null || activity.getWindow() == null) {
            return;
        }

        setStatusBarPaddingViewHeight(statusBarPaddingView);
        statusBarPaddingView.setBackgroundColor(statusBarBackgroundColor);
        statusBarPaddingView.setVisibility(drawBeneathStatusBar ? View.GONE : View.VISIBLE);


        // 对于无法设置透明状态栏，隐藏paddingView，使用默认的黑色状态栏
        if (!setTransparentStatusBar(activity.getWindow(), MODE_LIGHT)) {
            statusBarPaddingView.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化动态状态栏，>=LOLLIPOP或者是小米魅族时，activity content会延伸至状态栏下方，需要启用statusBarPaddingView
     */
    public static void initDynamicStatusBarBg(Window window, View statusBarPaddingView,
            int statusBarMode) {
        setStatusBarPaddingViewHeight(statusBarPaddingView);
        if (setTransparentStatusBar(window, statusBarMode)) {
            statusBarPaddingView.setVisibility(View.VISIBLE);
        } else {
            statusBarPaddingView.setVisibility(View.GONE);
        }
    }

    private static void setStatusBarPaddingViewHeight(View statusPaddingView) {
        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams) statusPaddingView.getLayoutParams();
        params.height = getStatusBarHeight(statusPaddingView.getContext());
        statusPaddingView.setLayoutParams(params);
    }

    private enum OSType {
        UNKNOWN, // 未知类型
        ABOVE_MARSHMALLOW, // Android 6.0 及以上
        MIUI, // 小米4.4及以上
        FLYME, // 魅族4.4及以上
        LOLLIPOP_BELOW_M, // 除上面之外，5.0及以上
        UNSUPPORTED // 不支持透明、半透明状态栏的系统，即除上面之外的系统
    }

    private static OSType osType = OSType.UNKNOWN;

    @IntDef({MODE_LIGHT, MODE_DARK})
    @Retention(RetentionPolicy.SOURCE)
    @interface StatusBarMode {
    }

    public static final int MODE_LIGHT = 0;
    public static final int MODE_DARK = 1;

    /**
     * 设置状态栏模式，状态栏图标和文字将变为深色/浅色，activity内容将延伸至状态栏下方
     *
     * @return true表示设置透明状态栏成功，false失败，状态栏icon颜色不保证能设置成功
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean setTransparentStatusBar(Window window,
            @StatusBarMode int statusBarMode) {
        if (window == null) {
            return false;
        }

        if (osType == OSType.UNKNOWN) {
            osType = getOSType();
        }

        switch (osType) {
            case ABOVE_MARSHMALLOW:
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                setAboveMashMallowStatusBarMode(window, statusBarMode == MODE_LIGHT);
                return true;
            case MIUI:
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                return setMiuiStatusBarMode(window, statusBarMode == MODE_LIGHT);
            case FLYME:
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                return setFlymeStatusBarMode(window, statusBarMode == MODE_LIGHT);
            case LOLLIPOP_BELOW_M:
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                return true;
            case UNSUPPORTED:
                return false;
            case UNKNOWN:
            default:
                return false;
        }
    }

    private static OSType getOSType() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return OSType.ABOVE_MARSHMALLOW;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return OSType.UNSUPPORTED;
        } else if (isSupportedMIUI()) {
            return OSType.MIUI;
        } else if (isSupportedFlyme()) {
            return OSType.FLYME;
        } else if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            return OSType.LOLLIPOP_BELOW_M;
        } else {
            return OSType.UNSUPPORTED;
        }
    }

    // region ABOVE_MARSHMALLOW

    @TargetApi(Build.VERSION_CODES.M)
    private static void setAboveMashMallowStatusBarMode(Window window, boolean dark) {
        View decor = window.getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(
                    // 这两个flag在6.0及以上系统上可以起到让activity内容延伸至状态栏下方的效果
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            // 这个Flag控制状态栏图标和文字的颜色，设置后为灰色
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    // endregion

    // region FLYME

    private static boolean isSupportedFlyme() {
        try {
            WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            return true;
        } catch (final NoSuchFieldException e) {
            return false;
        }
    }

    /**
     * 魅族Flyme系统设置状态栏模式
     */
    private static boolean setFlymeStatusBarMode(Window window, boolean darkIcon) {
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
                if (darkIcon) {
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

    // endregion

    // region MIUI

    /**
     * 小米MIUI系统设置状态栏模式
     */
    private static boolean setMiuiStatusBarMode(Window window, boolean darkIcon) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                @SuppressWarnings("unchecked") Method extraFlagField = clazz.getMethod(
                        "setExtraFlags", int.class, int.class);
                if (darkIcon) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag); // 状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag); // 清除黑色字体
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
            return versionName != null && "V6".compareToIgnoreCase(versionName) <= 0;
        } catch (final IOException e) {
            return false;
        }
    }

    // endregion

    private static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int result = 72; // 1920x1280
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 判断系统是否支持透明状态栏
     */
    public static boolean supportTransParentStatusBar() {
        if (osType == OSType.UNKNOWN) {
            osType = getOSType();
        }
        return osType != OSType.UNSUPPORTED;
    }
}
