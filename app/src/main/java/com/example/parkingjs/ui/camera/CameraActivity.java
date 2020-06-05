package com.example.parkingjs.ui.camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.parkingjs.R;
import com.example.parkingjs.databinding.ActivityCameraBinding;
import com.example.parkingjs.extension.tedpermission.PermissionUtil;
import com.example.parkingjs.ui.BaseActivity;
import com.gun0912.tedpermission.PermissionListener;

import java.util.List;

public class CameraActivity extends BaseActivity<ActivityCameraBinding> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		checkCameraHardware();
		initViews();
	}

	private void checkCameraHardware() {
		if (getApplicationContext()
				.getPackageManager()
				.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			PermissionUtil.requestPermission(getApplicationContext(), getPermissionListener(),
					Manifest.permission.CAMERA,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE);
		} else {
			Toast.makeText(getApplicationContext(), "해당 하드웨어에 카메라가 없습니다.", Toast.LENGTH_LONG).show();
			finish();
		}
	}

	private void initViews() {

	}

	private PermissionListener getPermissionListener() {
		return new PermissionListener() {
			@Override
			public void onPermissionGranted() {
				Toast.makeText(getApplicationContext(), "권한 허가되었습니다.", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onPermissionDenied(List<String> deniedPermissions) {
				Toast.makeText(getApplicationContext(), "다음 권한이 거부되어 실행할 수 없습니다.\n" +
						deniedPermissions.toString(), Toast.LENGTH_LONG).show();
				finish();
			}
		};
	}

	public static Intent getLaunchIntent(@NonNull Context context) {
		return new Intent(context, CameraActivity.class);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_camera;
	}
}