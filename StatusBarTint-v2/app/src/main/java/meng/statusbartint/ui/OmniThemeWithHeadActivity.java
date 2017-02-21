package meng.statusbartint.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

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
        StatusBarUtil.transparencyBar(this);
        setContentView(R.layout.activity_omni_theme_with_head);
        ButterKnife.bind(this);
        statusBarPaddingView.setVisibility(View.GONE);
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
                View contentView = scrollView.getChildAt(0);
                int maxScrollY = contentView.getHeight() - scrollView.getHeight();
                float collapsePercentage = ((float) y) / maxScrollY;
                int color = Color.argb((int) ((collapsePercentage) * 0xFF), 0xFF, 0xFF, 0xFF);
                navBarView.setBackgroundColor(color);
                if (collapsePercentage > 0.8) {
                    StatusBarUtil.setLightStatusBar(OmniThemeWithHeadActivity.this, true);
                } else {
                    StatusBarUtil.setLightStatusBar(OmniThemeWithHeadActivity.this, false);
                }
                StatusBarUtil.setStatusBarColor(OmniThemeWithHeadActivity.this, color);
            }
        });
    }

    public void onClick(View v) {

    }
}
