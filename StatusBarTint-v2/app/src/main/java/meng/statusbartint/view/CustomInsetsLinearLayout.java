package meng.statusbartint.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.LinearLayout;

import meng.statusbartint.util.StatusBarUtil;

/**
 * Created by meng on 2017/2/28.
 *
 * 因为Android系统的一个bug，导致设置透明状态栏后，adjustResize无法正常工作，使用此layout作为activity的根
 * 并设置android:fitSystemWindows="true"绕过这个bug
 *
 * Bug链接：https://code.google.com/p/android/issues/detail?id=5497&q=fullscreen&colspec=ID
 * %20Type%20Status%20Owner%20Summary%20Stars
 * 解决方案链接：http://stackoverflow.com/questions/21092888/windowsoftinputmode-adjustresize-not
 * -working-with-translucent-action-navbar/22266717#22266717
 */
public class CustomInsetsLinearLayout extends LinearLayout {

    private int[] mInsets = new int[4];

    public CustomInsetsLinearLayout(Context context) {
        super(context);
    }

    public CustomInsetsLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomInsetsLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final int[] getInsets() {
        return mInsets;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected final boolean fitSystemWindows(Rect insets) {
        if (StatusBarUtil.supportTransParentStatusBar()) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.
            mInsets[0] = insets.left;
            mInsets[1] = insets.top;
            mInsets[2] = insets.right;

            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
        }
        return super.fitSystemWindows(insets);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (StatusBarUtil.supportTransParentStatusBar()) {
            mInsets[0] = insets.getSystemWindowInsetLeft();
            mInsets[1] = insets.getSystemWindowInsetTop();
            mInsets[2] = insets.getSystemWindowInsetRight();
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
                    insets.getSystemWindowInsetBottom()));
        } else {
            return insets;
        }
    }
}
