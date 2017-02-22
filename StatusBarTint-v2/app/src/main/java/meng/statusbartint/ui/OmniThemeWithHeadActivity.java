package meng.statusbartint.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;
import meng.statusbartint.util.StatusBarUtil;
import meng.statusbartint.view.ObservableScrollView;

public class OmniThemeWithHeadActivity extends BaseActivity {

    @BindView(R.id.navbar)
    View navBarView;
    @BindView(R.id.status_bar_padding_view)
    View statusBarPaddingView;
    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.scroll_view)
    ObservableScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omni_theme_with_head);
        ButterKnife.bind(this);
        final boolean supportDynamicStatusBarBg = StatusBarUtil.isSupportDynamicStatusBarBg(this);
        if (supportDynamicStatusBarBg) {
            StatusBarUtil.transparencyBar(this);
            StatusBarUtil.setLightStatusBar(this, false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                statusBarPaddingView.setVisibility(View.GONE);
                LinearLayout.LayoutParams params =
                        (LinearLayout.LayoutParams) navBarView.getLayoutParams();
                params.setMargins(0, (int) (getResources().getDisplayMetrics().density * 24), 0, 0);
                navBarView.setLayoutParams(params);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarPaddingView.setVisibility(View.VISIBLE);
                statusBarPaddingView.setBackgroundColor(Color.parseColor("#00ffffff"));
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            statusBarPaddingView.setVisibility(View.VISIBLE);
            statusBarPaddingView.setBackgroundColor(Color.parseColor("#00ffffff"));
        } else {
            statusBarPaddingView.setVisibility(View.GONE);
        }
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
                View contentView = scrollView.getChildAt(0);
                int maxScrollY = contentView.getHeight() - scrollView.getHeight();
                float collapsePercentage = ((float) y) / maxScrollY;
                int color = Color.argb((int) ((collapsePercentage) * 0xFF), 0xFF, 0xFF, 0xFF);
                navBarView.setBackgroundColor(color);
                statusBarPaddingView.setBackgroundColor(color);
                if (supportDynamicStatusBarBg) {
                    StatusBarUtil.setLightStatusBar(OmniThemeWithHeadActivity.this,
                            collapsePercentage > 0.8);
                    StatusBarUtil.setStatusBarColor(OmniThemeWithHeadActivity.this, color);
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.bg_eh:
                Toast.makeText(this, "Clicked bg!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
