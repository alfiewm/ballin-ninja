package meng.imageviewer;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import meng.imageviewer.imageview.ImageProvider;
import meng.imageviewer.imageview.ImageSource;
import meng.imageviewer.imageview.ImageThumbnail;
import meng.imageviewer.util.BitmapUtils;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by meng on 2017/3/9.
 */
public class SingleImageFragment extends Fragment {

    private ImageProvider imageProvider;
    private int index;
    //    private Bitmap bitmap;
    private View downloadBtn;
    private ImageView imageView;
    private ImageView thumbView;
    private View errorView;
    private ProgressBar progressBar;
    private Target target;

    public SingleImageFragment() {
    }

    public void setParams(@NonNull ImageProvider imageProvider, int index) {
        this.imageProvider = imageProvider;
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: index = " + index);
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
        thumbView = (ImageView) rootView.findViewById(R.id.thumb_view);
        imageView = (ImageView) rootView.findViewById(R.id.image_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        errorView = rootView.findViewById(R.id.load_error_container);
        downloadBtn = rootView.findViewById(R.id.download_btn);
        setupDownloadBtn();
        setThumbnail();
        loadImage();
        return rootView;
    }

    private static final String TAG = "SingleImageFragment";

    private void loadImage() {
        final ImageSource imageSource = imageProvider.getImageSource(index);
        if (imageSource == null) {
            return;
        }
        if (!TextUtils.isEmpty(imageSource.getLocalPath())) {
            Log.d(TAG, "loadImage: loading from local path : " + imageSource.getLocalPath());
            Picasso.with(getActivity()).load(imageSource.getLocalPath()).into(imageView);
        } else if (!TextUtils.isEmpty(imageSource.getRemotePath())) {
            Log.d(TAG, "loadImage: loading from remote path : " + imageSource.getRemotePath());
            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Log.d(TAG, "onBitmapLoaded: " + imageSource.getRemotePath());
                    progressBar.setVisibility(View.GONE);
                    thumbView.setVisibility(View.GONE);
                    bitmap = BitmapUtils.scaleBitmap(bitmap, 4096, 4096, false);
//                    SingleImageFragment.this.bitmap = bitmap;
                    imageView.setImageBitmap(bitmap);
                    new PhotoViewAttacher(imageView).setOnPhotoTapListener(
                            new PhotoViewAttacher.OnPhotoTapListener() {
                                @Override
                                public void onPhotoTap(View view, float v, float v2) {
                                    exit();
                                }

                                @Override
                                public void onOutsidePhotoTap() {
                                    exit();
                                }
                            });
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    if (!isAdded()) {
                        return;
                    }
                    Log.d(TAG, "onBitmapFailed: " + imageSource.getRemotePath());
                    progressBar.setVisibility(View.GONE);
                    thumbView.setVisibility(View.GONE);
                    errorView.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Load Image Fail.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    // do nothing.
                }
            };
            Picasso.with(getActivity()).load(imageSource.getRemotePath()).into(target);
        }
    }

    private void setupDownloadBtn() {
        if (imageProvider.allowDownload()) {
            downloadBtn.setVisibility(View.VISIBLE);
            downloadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Downloading Function is in Progress.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            downloadBtn.setVisibility(View.GONE);
        }
    }

    private void exit() {
        if (getActivity() != null && isAdded()) {
            getActivity().finish();
        }
    }

    private void setThumbnail() {
        ImageThumbnail thumbnail = imageProvider.getThumbnail(index);
        if (thumbnail == null) {
            thumbView.setVisibility(View.GONE);
            return;
        }
        thumbView.setVisibility(View.VISIBLE);
        if (thumbnail.getBitmap() != null) {
            thumbView.setImageBitmap(thumbnail.getBitmap());
        } else if (!TextUtils.isEmpty(thumbnail.getLocalPath())) {
            Picasso.with(getActivity()).load(new File(thumbnail.getLocalPath())).into(thumbView);
        } else {
            thumbView.setVisibility(View.GONE);
        }
    }
}
