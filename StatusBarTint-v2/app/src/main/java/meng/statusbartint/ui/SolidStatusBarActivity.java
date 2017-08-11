package meng.statusbartint.ui;

import static meng.statusbartint.R.id.back_btn;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;
import meng.statusbartint.util.StatusBarUtil;

public class SolidStatusBarActivity extends BaseActivity implements View.OnClickListener {

    private boolean lightStatusBar;
    private static int[] solid_colors = {Color.WHITE, Color.RED, Color.parseColor("#008000"),
            Color.BLUE};
    private int index;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.title)
    TextView titleView;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_solid_status_bar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == back_btn) {
            finish();
        } else if (v.getId() == R.id.switch_status_bar_icon) {
            lightStatusBar = !lightStatusBar;
            StatusBarUtil.setStatusBarMode(getWindow(),
                    lightStatusBar ? StatusBarUtil.MODE_LIGHT : StatusBarUtil.MODE_DARK);
        } else if (v.getId() == R.id.switch_status_bar_color) {
            int color = solid_colors[(index++) % solid_colors.length];
            switchNavBarAndStatusBarBg(color);
        } else if (v.getId() == R.id.btn_pop_window) {
            popWindow();
        }
    }

    private void popWindow() {
        final View popView = LayoutInflater.from(this).inflate(R.layout.view_popup, null, false);

        final PopupWindow popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        View groundView = popView.findViewById(R.id.pop_root);
        groundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(backBtn, Gravity.CENTER, 0, 0);
    }

    private void switchNavBarAndStatusBarBg(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
        if (color == Color.WHITE) {
            backBtn.setImageResource(R.drawable.arrow_back_black);
            titleView.setTextColor(Color.BLACK);
            StatusBarUtil.setStatusBarMode(getWindow(), StatusBarUtil.MODE_LIGHT);
        } else {
            backBtn.setImageResource(R.mipmap.ic_back);
            titleView.setTextColor(Color.WHITE);
            StatusBarUtil.setStatusBarMode(getWindow(), StatusBarUtil.MODE_DARK);
        }
        findViewById(R.id.navbar).setBackgroundColor(color);
    }
}
