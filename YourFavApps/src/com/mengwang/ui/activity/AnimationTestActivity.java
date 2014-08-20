package com.mengwang.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mengwang.guessyourfav.R;
import com.mengwang.ui.view.StatusBar;

public class AnimationTestActivity extends Activity {

	private StatusBar statusBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_test);
		statusBar = (StatusBar) findViewById(R.id.status_bar);
		statusBar.initViews();
		initViews();
	}

	private void initViews() {
		statusBar.setPhotoStatusView(R.string.upload_photo, R.string.ok, null);
		statusBar.setDLStatusView(R.string.verify_dl_license, R.string.ok, null);
	}
	
	public void onClick(View v) {
		int randomNum = (int) (Math.random() * 4);
		switch (randomNum) {
		case 0:
			statusBar.setPhotoStatusView(R.string.photo_pending_review, 0, null);
			statusBar.setDLStatusView(R.string.dl_pending_review, 0, null);
			break;
		case 1:
			statusBar.hidePhotoStatusView();
			statusBar.setDLStatusView(R.string.dl_rejected, R.string.ok, null);
			break;
		case 2:
			statusBar.setPhotoStatusView(R.string.photo_rejected, R.string.ok, null);
			statusBar.hideDLStatusView();
			break;
		case 3:
			statusBar.hidePhotoStatusView();
			statusBar.hideDLStatusView();
			break;
		default:
			break;
		}
	}

}
