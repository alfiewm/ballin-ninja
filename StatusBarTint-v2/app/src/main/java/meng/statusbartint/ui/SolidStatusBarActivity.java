package meng.statusbartint.ui;

import static meng.statusbartint.R.id.back_btn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
            lightStatusBar = !lightStatusBar;
            StatusBarUtil.setLightStatusBar(this, lightStatusBar);
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
            StatusBarUtil.setLightStatusBar(this, true);
        } else {
            backBtn.setImageResource(R.mipmap.ic_back);
            titleView.setTextColor(Color.WHITE);
            StatusBarUtil.setLightStatusBar(this, false);
        }
        findViewById(R.id.navbar).setBackgroundColor(color);
    }
}
