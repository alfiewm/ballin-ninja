package meng.statusbartint.util;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
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
     * 设置透明状态栏，6.0以上为全透明，6.0以下为半透明
     */
    @SuppressLint("InlinedApi")
    public static boolean setTransparentStatusBar(Window window) {
        if (window == null) {
            return false;
        }
        ensureOSType();
        setStatusBarColor(window, Color.TRANSPARENT);
        switch (osType) {
            case ABOVE_MARSHMALLOW:
                window.getDecorView().setSystemUiVisibility(
                        window.getDecorView().getSystemUiVisibility() |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                return true;
            case MIUI:
            case FLYME:
            case LOLLIPOP_BELOW_M:
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                return true;
            default:
                return false;
        }
    }

    /**
     * 初始化activity
     */
    public static void initStatusBar(Activity activity,
            boolean drawBeneathStatusBar, @ColorInt int statusBarColor,
            @StatusBarMode int statusBarMode) {
        if (activity == null || activity.getWindow() == null) {
            return;
        }
        if (drawBeneathStatusBar) {
            setTransparentStatusBar(activity.getWindow());
        }
        setStatusBarColor(activity.getWindow(), statusBarColor);
        setStatusBarMode(activity.getWindow(), statusBarMode);
    }

    /**
     * 初始化渐变状态栏activity
     */
    public static void initDynamicStatusBarBg(Window window, View statusBarPaddingView,
            @StatusBarMode int statusBarMode) {
        setStatusBarPaddingViewHeight(statusBarPaddingView);
        if (setTransparentStatusBar(window)) {
            setStatusBarMode(window, statusBarMode);
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

    public static boolean setLightTransparentStatusBar(Window window) {
        return setTransparentStatusBar(window) && setStatusBarMode(window, MODE_LIGHT);
    }

    /**
     * 设置状态栏模式，状态栏图标和文字将变为深色/浅色
     *
     * @return true表示设置状态栏模式成功，false失败
     */
    @SuppressLint("NewApi")
    public static boolean setStatusBarMode(Window window, @StatusBarMode int statusBarMode) {
        if (window == null) {
            return false;
        }
        ensureOSType();
        switch (osType) {
            case ABOVE_MARSHMALLOW:
                return setAboveMashMallowStatusBarMode(window, statusBarMode == MODE_LIGHT);
            case MIUI:
                return setMiuiStatusBarMode(window, statusBarMode == MODE_LIGHT);
            case FLYME:
                return setFlymeStatusBarMode(window, statusBarMode == MODE_LIGHT);
            case LOLLIPOP_BELOW_M:
            case UNSUPPORTED:
            case UNKNOWN:
            default:
                return false;
        }
    }

    /**
     * 设置状态栏颜色，支持5.0及以上系统
     */
    private static boolean setStatusBarColor(@NonNull Window window, int bgColor) {
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(bgColor);
            return true;
        }
        return false;
    }

    // region ABOVE_MARSHMALLOW

    private static boolean setAboveMashMallowStatusBarMode(Window window, boolean darkIcon) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        View decor = window.getDecorView();
        if (darkIcon) {
            decor.setSystemUiVisibility(
                    decor.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(
                    decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        return true;
    }

    // endregion

    // region FLYME

    private static boolean isSupportedFlyme() {
        if (Build.VERSION.SDK_INT < LOLLIPOP) {
            return false;
        }
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
        if (Build.VERSION.SDK_INT < LOLLIPOP) {
            return false;
        }
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
        ensureOSType();
        return osType != OSType.UNSUPPORTED;
    }

    /**
     * 是否支持浅色状态栏
     */
    public static boolean supportLightStatusBar() {
        ensureOSType();
        return osType == OSType.ABOVE_MARSHMALLOW || osType == OSType.MIUI
                || osType == OSType.FLYME;
    }

    private static void ensureOSType() {
        if (osType == OSType.UNKNOWN) {
            osType = getOSType();
        }
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
}
