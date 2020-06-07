package com.dankook.parkingjs.ui.camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.dankook.parkingjs.R;
import com.dankook.parkingjs.databinding.ActivityCameraBinding;
import com.dankook.parkingjs.extension.tedpermission.PermissionUtil;
import com.dankook.parkingjs.ui.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class CameraActivity extends BaseActivity<ActivityCameraBinding> {

	public static final String TAG = "CameraActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
		| WindowManager.LayoutParams.FLAG_FULLSCREEN);

		checkCameraHardware();
		initViews();
		initViewModel();
		startCaptureTimer();
	}

	private void initViews() {
		binding.viewFinder.setImageCaptureButton(binding.ivCamera);
	}

	private void initViewModel() {
		binding.setVm(ViewModelProvider.AndroidViewModelFactory
				.getInstance(getApplication()).create(CameraViewModel.class));
	}

	private void startCaptureTimer() {
		TimerTask captureTask = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(() -> {
					Log.d(TAG, "click");
					binding.ivCamera.performClick();
				});
			}
		};

		Timer timer = new Timer();
		timer.schedule(captureTask, 10000, 10000);
	}

	private void checkCameraHardware() {
		if (getApplicationContext()
				.getPackageManager()
				.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			PermissionUtil.requestPermission(getApplicationContext(),
					Manifest.permission.CAMERA,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE);
		} else {
			Toast.makeText(getApplicationContext(), "해당 하드웨어에 카메라가 없습니다.",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}

	public static Intent getLaunchIntent(@NonNull Context context) {
		return new Intent(context, CameraActivity.class);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_camera;
	}
}