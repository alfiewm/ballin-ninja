package meng.statusbartint.ui;

import static meng.statusbartint.R.id.back_btn;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
            StatusBarUtil.setLightStatusBar(this, lightStatusBar);
        } else if (v.getId() == R.id.switch_status_bar_color) {
            int color = solid_colors[(index++) % solid_colors.length];
            switchNavBarAndStatusBarBg(color);
        }
    }

    private void switchNavBarAndStatusBarBg(int color) {
        StatusBarUtil.setStatusBarColor(this, color);
        if (color == Color.WHITE) {
            backBtn.setImageResource(R.drawable.arrow_back_black);
            titleView.setTextColor(Color.BLACK);
            StatusBarUtil.setLightStatusBar(this, true);
        } else {
            backBtn.setImageResource(R.mipmap.ic_back);
            titleView.setTextColor(Color.WHITE);
            StatusBarUtil.setLightStatusBar(this, false);
        }
        findViewById(R.id.navbar).setBackgroundColor(color);
    }
}
