package meng.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import meng.db.widget.ptr.PtrFrameLayout;
import meng.db.widget.ptr.PtrHandler;

public class PullRefreshActivity extends AppCompatActivity {

    PtrFrameLayout ptrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);
        ptrFrame = (PtrFrameLayout) findViewById(R.id.root);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 1000);
            }
        });
    }
}
