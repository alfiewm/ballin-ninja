package com.mengwang.ui.activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mengwang.guessyourfav.R;

public class UsingCameraActivity extends Activity {
	public static final String TAG = "UsingcameraActivity";
	public static final int REQUEST_SYSTEM_CAMERA = 0;

	private ImageView previewImageView;
	private String imagePath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_using_camera);
		initViews();
	}

	private void initViews() {
		previewImageView = (ImageView) findViewById(R.id.captured_image);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.from_system:
			dispatchTakePictureIntent();
			break;
		case R.id.customize_camera:
			break;
		}
	}

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			File imageFile = null;
			try {
				imageFile = createPublicImageFile();
				// imageFile = createPrivateImageFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (imageFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
				startActivityForResult(takePictureIntent, REQUEST_SYSTEM_CAMERA);
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_SYSTEM_CAMERA && resultCode == RESULT_OK) {
			if (imagePath != null) {
				setPic();
				galleryAddPic();
				imagePath = null;
			}
		}
	}

	private void setPic() {
		int targetWidth = previewImageView.getWidth();
		int targetHeight = previewImageView.getHeight();

		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imagePath, bitmapOptions);

		int scaleFactor = calculateInSampleSize(bitmapOptions, targetHeight, targetWidth);
		Log.d(TAG, " target height & width & sample Size = " + targetHeight + "X" + targetWidth + "  " + scaleFactor);

		scaleFactor = (scaleFactor < 1) ? 1 : scaleFactor;

		bitmapOptions.inSampleSize = scaleFactor;
		bitmapOptions.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bitmapOptions);
		if (bitmap != null) {
			previewImageView.setImageBitmap(bitmap);
		}
	}

	private File createPublicImageFile() throws IOException {
		// create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, ".jpg", storageDir);
		imagePath = image.getAbsolutePath();
		Log.d(TAG, "external storage public directory image path = " + imagePath);
		return image;
	}

	private File createPrivateImageFile() throws IOException {
		String fileName = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/"
				+ String.valueOf(System.currentTimeMillis()) + "*.jpg";
		File image = new File(fileName);
		if (image.exists()) {
			image.delete();
			image.createNewFile();
		} else {
			image.createNewFile();
		}
		imagePath = image.getAbsolutePath();
		Log.d(TAG, "private storage image path = " + imagePath);
		return image;
	}

	/**
	 * invoke the system scanner to add the photo to the media provider's
	 * database, making it available in Android Gallery application and to other
	 * apps
	 */
	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(imagePath);
		mediaScanIntent.setData(Uri.fromFile(f));
		sendBroadcast(mediaScanIntent);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
		final int actHalfHeight = options.outHeight / 2;
		final int actHalfWidth = options.outWidth / 2;

		int inSampleSize = 1;
		while (actHalfHeight / inSampleSize > reqHeight && actHalfWidth / inSampleSize > reqWidth) {
			inSampleSize *= 2;
		}
		return inSampleSize;
	}
}
