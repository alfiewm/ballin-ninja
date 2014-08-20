package com.mengwang.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mengwang.guessyourfav.R;

/**
 * Filename: StatusBar.java
 * 
 * Description: Custom view for managing photo approval status bar and driver's
 * license status bar
 * 
 * Created Date: June 17, 2014
 * 
 * Author : mengwang
 * 
 * Copyright (C) MicroStrategy Incorporated 2014 All Rights Reserved
 */
public class StatusBar extends RelativeLayout {

	// photo status views
	private View photoStatusBar;
	private TextView photoStatusTextView;
	private View photoDivider;
	private TextView photoButton;

	// driver's license status views
	private View dlStatusBar;
	private TextView dlStatusTextView;
	private View dlDivider;
	private TextView dlButton;

	private ImageView foldButton;

	private boolean isPhotoStatusSet = false;
	private boolean isDLStatusSet = false;
	private static boolean isFold = false;
	private Animation inAnimation;
	private Animation outAnimation;
	private Animation upAnimation;
	private Animation downAnimation;

	private enum FoldStatus {
		NONE, FOLD, UNFOLD
	};

	public StatusBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		inAnimation = AnimationUtils.loadAnimation(context, R.anim.appear);
		outAnimation = AnimationUtils.loadAnimation(context, R.anim.disappear);
		upAnimation = AnimationUtils.loadAnimation(context, R.anim.up);
		downAnimation = AnimationUtils.loadAnimation(context, R.anim.down);
	}

	// this method must be called before any action took on this view, or use property animator
	public void initViews() {
		photoStatusBar = findViewById(R.id.photo_status_bar);
		photoStatusBar.setVisibility(View.GONE);
		photoStatusTextView = (TextView) findViewById(R.id.photo_status_message);
		photoDivider = findViewById(R.id.photo_status_divider);
		photoButton = (TextView) findViewById(R.id.photo_status_button);

		dlStatusBar = findViewById(R.id.dl_status_bar);
		dlStatusBar.setVisibility(View.GONE);
		dlStatusTextView = (TextView) findViewById(R.id.dl_status_message);
		dlDivider = findViewById(R.id.dl_status_divider);
		dlButton = (TextView) findViewById(R.id.dl_status_button);

		foldButton = (ImageView) findViewById(R.id.fold_button);
		foldButton.setVisibility(View.GONE);
		foldButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (getFoldStatus()) {
				case FOLD:
					unFoldViews();
					break;
				case UNFOLD:
					foldViews();
					break;
				case NONE:
				default:
					break;
				}
			}
		});
	}

	private FoldStatus getFoldStatus() {
		if (!isPhotoStatusSet || !isDLStatusSet) {
			return FoldStatus.NONE;
		}

		if (photoStatusBar.getVisibility() == VISIBLE && dlStatusBar.getVisibility() == VISIBLE) {
			return FoldStatus.UNFOLD;
		} else {
			return FoldStatus.FOLD;
		}
	}

	private void unFoldViews() {
		isFold = false;
		// maybe set fixed height? or linear layout or 
		upAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				dlStatusBar.startAnimation(inAnimation);
				dlStatusBar.setVisibility(View.VISIBLE);
			}
		});
		photoStatusBar.startAnimation(upAnimation);
		foldButton.setImageResource(R.drawable.btn_fold);
	}

	private void foldViews() {
		isFold = true;
		outAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				downAnimation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						dlStatusBar.setVisibility(View.GONE);
					}
				});
				photoStatusBar.startAnimation(downAnimation);
			}
		});
		dlStatusBar.startAnimation(outAnimation);
		foldButton.setImageResource(R.drawable.btn_unfold);
	}

	private void refresh() {
		switch (getFoldStatus()) {
		case UNFOLD:
			foldButton.setVisibility(View.VISIBLE);
			foldButton.setImageResource(R.drawable.btn_fold);
			break;
		case FOLD:
			foldButton.setVisibility(View.VISIBLE);
			foldButton.setImageResource(R.drawable.btn_unfold);
			break;
		case NONE:
		default:
			foldButton.setVisibility(View.GONE);
			break;
		}

		if (!isDLStatusSet && !isPhotoStatusSet) {
			setVisibility(View.GONE);
		} else {
			setVisibility(View.VISIBLE);
		}
	}

	public void setPhotoStatusView(int msgId, int btnMsgId, OnClickListener onClickListener) {
		photoStatusBar.setVisibility(View.VISIBLE);
		photoStatusTextView.setText(msgId);

		if (onClickListener == null) {
			photoDivider.setVisibility(View.GONE);
			photoButton.setVisibility(View.GONE);
		} else {
			photoDivider.setVisibility(View.VISIBLE);
			photoButton.setVisibility(View.VISIBLE);
			photoButton.setText(btnMsgId);
			photoButton.setOnClickListener(onClickListener);
		}

		isPhotoStatusSet = true;
		refresh();
	}

	public void hidePhotoStatusView() {
		photoStatusBar.setVisibility(View.GONE);
		isPhotoStatusSet = false;

		if (isDLStatusSet) {
			// reveal the hidden driver license status bar
			dlStatusBar.setVisibility(View.VISIBLE);
		}
		refresh();
	}

	public void setDLStatusView(int msgId, int btnMsgId, OnClickListener onClickListener) {
		// mengwang TQMS 917720, keep fold state when reset status bar
		if (isFold && isPhotoStatusSet) {
			dlStatusBar.setVisibility(View.GONE);
		} else {
			dlStatusBar.setVisibility(View.VISIBLE);
		}
		dlStatusTextView.setText(msgId);

		if (onClickListener == null) {
			dlDivider.setVisibility(View.GONE);
			dlButton.setVisibility(View.GONE);
		} else {
			dlDivider.setVisibility(View.VISIBLE);
			dlButton.setVisibility(View.VISIBLE);
			dlButton.setText(btnMsgId);
			dlButton.setOnClickListener(onClickListener);
		}

		isDLStatusSet = true;
		refresh();
	}

	public void hideDLStatusView() {
		dlStatusBar.setVisibility(View.GONE);
		isDLStatusSet = false;
		refresh();
	}

}
