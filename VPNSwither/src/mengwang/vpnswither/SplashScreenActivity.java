package mengwang.vpnswither;

import mengwang.vpnswither.view.SplashView;
import android.app.Activity;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	private SplashView splashView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meng_test);

		splashView = (SplashView) findViewById(R.id.splash);
		splashView.setSvgResource(R.raw.vpnswitcher);
	}
}
