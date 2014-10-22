package mengwang.vpnswither;

import mengwang.vpnswither.view.SplashView;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

public class SplashScreenActivity extends Activity {
	public static final int NOTIFICATION_ID = 0;

	private SplashView splashView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meng_test);

		splashView = (SplashView) findViewById(R.id.splash);
		splashView.setSvgResource(R.raw.vpnswitcher);
		
		// TODO add a broadcast receiver to receive system start event and issue the notification;
	}

	@Override
	protected void onResume() {
		super.onResume();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				issueNotification();
				openSystemVpnSettings();
			}
		}, splashView.getDuration());
	}

	@SuppressWarnings("deprecation")
	public void issueNotification() {
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, getSystemVPNSettingsIntent(), PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				.setContentTitle(getString(R.string.app_name)).setContentText(getString(R.string.notification_content))
				.setSmallIcon(R.drawable.ic_launcher).setContentIntent(pendingIntent);

		notificationManager.notify(NOTIFICATION_ID,
				Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ? builder.build() : builder.getNotification());
	}

	private void openSystemVpnSettings() {
		startActivity(getSystemVPNSettingsIntent());
		finish();
	}
	
	private Intent getSystemVPNSettingsIntent() {
		Intent intent = new Intent("android.net.vpn.SETTINGS");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		return intent;
	}
}
