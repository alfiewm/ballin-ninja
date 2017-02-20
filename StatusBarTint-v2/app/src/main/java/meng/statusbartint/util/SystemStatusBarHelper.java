package meng.statusbartint.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * @author wanghb
 * @date 16/7/27.
 */
public class SystemStatusBarHelper {
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        if (activity == null || activity.getWindow() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static void setStatusBarColor(Activity activity) {
        // 1. 如果是6.0及以上系统，设置lightstatusbar，并设置浅色背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(Color.WHITE);
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            return;
        }

        // 设置透明状态栏,后面三种case都需要
        // 需要在xml中设置fitsystemwindows，这样statusbar下面透出的是activity的背景色。
        // 如果不设的话，需要手动给所有activity加一个paddingTopView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 2. 如果是MIUI v6及以上系统，设置透明深色icon模式
        if (StatusBarUtil.isMIUIV6()) {
            StatusBarUtil.MIUISetStatusBarLightMode(activity.getWindow(), true);
            return;
        }
        // 3. 如果是Flyme系统，同上尝试设置透明深色icon模式
        if (StatusBarUtil.isFlyme()) {
            StatusBarUtil.FlymeSetStatusBarLightMode(activity.getWindow(), true);
            return;
        }
        // 4. 如果是其他系统>=4.4，设置半透明statusbar
        // 前面已经设置过
    }
}
