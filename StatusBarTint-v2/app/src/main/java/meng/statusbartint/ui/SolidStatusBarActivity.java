package meng.statusbartint.ui;

import static meng.statusbartint.R.id.back_btn;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
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
            StatusBarUtil.setTransparentStatusBar(getWindow(), lightStatusBar ? StatusBarUtil.MODE_LIGHT : StatusBarUtil.MODE_DARK);
        } else if (v.getId() == R.id.switch_status_bar_color) {
            int color = solid_colors[(index++) % solid_colors.length];
            switchNavBarAndStatusBarBg(color);
        }
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
            StatusBarUtil.setTransparentStatusBar(getWindow(), StatusBarUtil.MODE_LIGHT);
        } else {
            backBtn.setImageResource(R.mipmap.ic_back);
            titleView.setTextColor(Color.WHITE);
            StatusBarUtil.setTransparentStatusBar(getWindow(), StatusBarUtil.MODE_DARK);
        }
        findViewById(R.id.navbar).setBackgroundColor(color);
    }
}
