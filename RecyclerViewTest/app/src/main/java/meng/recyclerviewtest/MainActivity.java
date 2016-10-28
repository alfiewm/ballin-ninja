package meng.recyclerviewtest;

import android.os.Bundle;

import meng.recyclerviewtest.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment(), "main").commit();
        }
    }
}
