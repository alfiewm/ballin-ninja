package com.mengwang.ui.activity;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mengwang.guessyourfav.R;

public class FavMainActivity extends Activity {

	private TextView runningAppsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fav_main);
		runningAppsTextView = (TextView) findViewById(R.id.running_lists);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.settings:
			showRunningActivities();
			break;
		default:
			break;
		}
	}

	private void showRunningActivities() {
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		// Get currently running application processes
		List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				runningAppsTextView.append(list.get(i).processName + "\n");
			}
		}
	}
}
