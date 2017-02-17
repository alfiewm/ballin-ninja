package meng.statusbartint.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
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
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public static void setStatusBarColor(Activity activity) {
        setStatusBarColor(activity, Color.RED);
    }
}
