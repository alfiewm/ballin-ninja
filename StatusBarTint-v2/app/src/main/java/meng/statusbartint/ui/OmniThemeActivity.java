package meng.statusbartint.ui;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import meng.statusbartint.R;
import meng.statusbartint.base.BaseActivity;

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
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Material_Light_NoActionBar_TranslucentDecor);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_view);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.getWindow().setBackgroundDrawable(
                getResources().getDrawable(R.drawable.dialog_background_transparent));
//        StatusBarUtil.setLightTransparentStatusBar(dialog.getWindow());
        dialog.show();
    }
}
