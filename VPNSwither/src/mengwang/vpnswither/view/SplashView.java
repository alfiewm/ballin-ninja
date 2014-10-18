package mengwang.vpnswither.view;

import java.util.ArrayList;
import java.util.List;

import mengwang.vpnswither.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SplashView extends View {
	private static final String LOG_TAG = "IntroView";

	private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private final SvgHelper mSvg = new SvgHelper(mPaint);
	private int mSvgResource;

	private final Object mSvgLock = new Object();
	private List<SvgHelper.SvgPath> mPaths = new ArrayList<SvgHelper.SvgPath>(0);
	private Thread mLoader;
	private float mPhase;
	private int mDuration;
	private float mFadeFactor;

	private ObjectAnimator mSvgAnimator;
	private OnReadyListener mListener;

	public static interface OnReadyListener {
		void onReady();
	}

	public SplashView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SplashView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SplashView, defStyle, 0);
		try {
			if (a != null) {
				mPaint.setStrokeWidth(a.getFloat(R.styleable.SplashView_strokeWidth, 1.0f));
				mPaint.setColor(a.getColor(R.styleable.SplashView_strokeColor, 0xff000000));
				mPhase = a.getFloat(R.styleable.SplashView_phase, 1.0f);
				mDuration = a.getInt(R.styleable.SplashView_duration, 4000);
				mFadeFactor = a.getFloat(R.styleable.SplashView_fadeFactor, 10.0f);
			}
		} finally {
			if (a != null)
				a.recycle();
		}

		init();
	}

	private void init() {
		mPaint.setStyle(Paint.Style.STROKE);
		setLayerType(LAYER_TYPE_SOFTWARE, null);

		mSvgAnimator = ObjectAnimator.ofFloat(this, "phase", mPhase, 0.0f).setDuration(mDuration);
	}

	public void setSvgResource(int resource) {
		if (mSvgResource == 0) {
			mSvgResource = resource;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		synchronized (mSvgLock) {
			canvas.save();
			canvas.translate(getPaddingLeft(), getPaddingTop() - getPaddingBottom());
			final int count = mPaths.size();
			for (int i = 0; i < count; i++) {
				SvgHelper.SvgPath svgPath = mPaths.get(i);

				// We use the fade factor to speed up the alpha animation
				int alpha = (int) (Math.min((1.0f - mPhase) * mFadeFactor, 1.0f) * 255.0f);
				svgPath.paint.setAlpha(alpha);

				canvas.drawPath(svgPath.path, svgPath.paint);
			}
			canvas.restore();
		}
	}

	@Override
	protected void onSizeChanged(final int w, final int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		if (mLoader != null) {
			try {
				// 阻塞当前线程，等待mLoader结束后才能继续执行当前线程
				mLoader.join();
			} catch (InterruptedException e) {
				Log.e(LOG_TAG, "Unexpected error", e);
			}
		}

		mLoader = new Thread(new Runnable() {
			@Override
			public void run() {
				mSvg.load(getContext(), mSvgResource);
				synchronized (mSvgLock) {
					mPaths = mSvg.getPathsForViewport(w - getPaddingLeft() - getPaddingRight(), h - getPaddingTop()
							- getPaddingBottom());
					updatePathsPhaseLocked();
				}
				post(new Runnable() {
					@Override
					public void run() {
						invokeReadyListener();
						if (mSvgAnimator.isRunning())
							mSvgAnimator.cancel();
						mSvgAnimator.start();
					}
				});
			}
		}, "SVG Loader");
		mLoader.start();
	}

	private void invokeReadyListener() {
		if (mListener != null)
			mListener.onReady();
	}

	public void setOnReadyListener(OnReadyListener listener) {
		mListener = listener;
	}

	private void updatePathsPhaseLocked() {
		final int count = mPaths.size();
		for (int i = 0; i < count; i++) {
			SvgHelper.SvgPath svgPath = mPaths.get(i);
			svgPath.paint.setPathEffect(createPathEffect(svgPath.length, mPhase, 0.0f));
		}
	}

	public float getPhase() {
		return mPhase;
	}

	public void setPhase(float phase) {
		mPhase = phase;
		synchronized (mSvgLock) {
			updatePathsPhaseLocked();
		}
		invalidate();
	}

	private static PathEffect createPathEffect(float pathLength, float phase, float offset) {
		return new DashPathEffect(new float[] { pathLength, pathLength }, Math.max(phase * pathLength, offset));
	}
}
