package meng.statusbartint.ui;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.view.View;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;
import meng.statusbartint.util.StatusBarUtil;

public class OmniThemeActivity extends BaseActivity {

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_omni_theme;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.bg_eh:
                showDialog();
                break;
            default:
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showDialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_view);
        dialog.findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog.show();
            }
        });
        StatusBarUtil.setLightTransparentStatusBar(dialog.getWindow());
        dialog.show();
    }
}
