package com.dankook.parkingjs.ui.camera;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageCapture;

import com.dankook.parkingjs.extension.retrofit.RetrofitClient;
import com.dankook.parkingjs.ui.BaseViewModel;

import org.apache.commons.lang3.ObjectUtils;

import java.io.File;

import javax.annotation.Nonnull;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraViewModel extends BaseViewModel {

	public static final String TAG = "CameraViewModel";

	public ImageCapture.OnImageSavedListener setOnImageCaptureListener() {
		return new ImageCapture.OnImageSavedListener() {
			@Override
			public void onImageSaved(@NonNull File file) {
				Log.d(TAG, file.getAbsolutePath());
				sendImage(file);
			}

			@Override
			public void onError(@NonNull ImageCapture.ImageCaptureError imageCaptureError,
			                    @NonNull String message, @Nullable Throwable cause) {
				Log.e(TAG, message);
			}
		};
	}

	private void sendImage(File file) {
		MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(),
						RequestBody.create(MediaType.parse("image/jpeg"), file));

		Call<Integer> call = RetrofitClient.api.sendImage(body);

		call.enqueue(new Callback<Integer>() {
			@Override
			public void onResponse(@Nonnull Call<Integer> call,
			                       @Nonnull Response<Integer> response) {
				if(ObjectUtils.isNotEmpty(response.body())) {
					int status = response.body();
					Log.d(TAG, "status : " + status);
				} else {
					Log.e(TAG, "Response body is NULL");
				}
			}

			@Override
			public void onFailure(@Nonnull Call<Integer> call,
			                      @Nonnull Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
