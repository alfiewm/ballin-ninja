package com.mengwang.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mengwang.guessyourfav.R;

public class FavMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fav_main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.control_camera:
			Intent it = new Intent(this, UsingCameraActivity.class);
			startActivity(it);
			break;
		case R.id.animation_test:
			startActivity(new Intent(this, AnimationTestActivity.class));
			break;
		case R.id.swipe_refresh:
			startActivity(new Intent(this, SwipeRefreshActivity.class));
			break;
		case R.id.shape_test:
			startActivity(new Intent(this, ShapeTestActivity.class));
			break;
		default:
			break;
		}
	}
}
