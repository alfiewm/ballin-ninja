package mengwang.vpnswither;

import mengwang.vpnswither.view.SplashView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

	private SplashView splashView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meng_test);

		splashView = (SplashView) findViewById(R.id.splash);
		splashView.setSvgResource(R.raw.vpnswitcher);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent it = new Intent(android.provider.Settings.ACTION_APN_SETTINGS);
				startActivity(it);
				finish();
			}
		}, 10000);
	}
}
