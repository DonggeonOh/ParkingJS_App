package com.dankook.parkingjs.ui.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Rational;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.lifecycle.LifecycleOwner;

import com.dankook.parkingjs.utils.FileUtil;

import java.lang.ref.WeakReference;

@SuppressLint("RestrictedApi")
public class CameraView extends TextureView {

	private static final Rational rational = new Rational(3, 4);
	private static final CameraX.LensFacing lensFacing = CameraX.LensFacing.BACK;

	private Preview preview;
	private ImageCapture imageCapture;
	private Context context;

	private WeakReference<View> captureButton;

	public CameraView(Context context) {
		super(context);
		initView(context);
	}

	public CameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(@NonNull final Context context) {
		this.context = context;

		post(() -> {
			getLayoutParams().height = getWidth() / rational.getNumerator() * rational.getDenominator();
			requestLayout();
		});

		post(this::bindCameraUseCases);
	}

	private void bindCameraUseCases() {
		bindCameraPreview();
		bindImageCapture();

		CameraX.bindToLifecycle((LifecycleOwner) context, preview, imageCapture);
	}

	private void bindCameraPreview() {
		final PreviewConfig.Builder builder = new PreviewConfig.Builder()
				.setLensFacing(lensFacing)
				.setTargetAspectRatio(rational)
				.setTargetRotation(getDisplay().getRotation());

		if (preview != null) {
			CameraX.unbind(preview);
		}

		preview = CameraPreviewBuilder.build(builder.build(), this);
	}

	private void bindImageCapture() {
		final ImageCaptureConfig.Builder builder = new ImageCaptureConfig.Builder()
				.setLensFacing(lensFacing)
				.setTargetAspectRatio(rational)
				.setTargetRotation(getDisplay().getRotation())
				.setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY);

		if (imageCapture != null) {
			CameraX.unbind(imageCapture);
		}

		imageCapture = new ImageCapture(builder.build());
	}

	public void setImageCaptureButton(@NonNull final View view) {
		this.captureButton = new WeakReference<>(view);
	}

	public void setImageCaptureListener(@NonNull final ImageCapture.OnImageSavedListener listener) {
		if (captureButton == null || captureButton.get() == null) {
			return;
		}

		captureButton.get().setOnClickListener(__ ->
				imageCapture.takePicture(FileUtil.createNewFile(), listener));
	}
}