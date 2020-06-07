package com.dankook.parkingjs.ui.camera;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageCapture;

import com.dankook.parkingjs.ui.BaseViewModel;

import java.io.File;

public class CameraViewModel extends BaseViewModel {

	public static final String TAG = "CameraViewModel";

	public ImageCapture.OnImageSavedListener setOnImageCaptureListener() {
		return new ImageCapture.OnImageSavedListener() {
			@Override
			public void onImageSaved(@NonNull File file) {
				Log.d(TAG, file.getAbsolutePath());
			}

			@Override
			public void onError(@NonNull ImageCapture.ImageCaptureError imageCaptureError,
			                    @NonNull String message, @Nullable Throwable cause) {
				Log.e(TAG, message);
			}
		};
	}
}
