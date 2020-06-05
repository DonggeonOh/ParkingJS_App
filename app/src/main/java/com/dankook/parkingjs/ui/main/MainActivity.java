package com.dankook.parkingjs.ui.main;

import android.os.Bundle;

import com.dankook.parkingjs.R;
import com.dankook.parkingjs.databinding.ActivityMainBinding;
import com.dankook.parkingjs.ui.BaseActivity;
import com.dankook.parkingjs.ui.camera.CameraActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViews();
	}

	private void initViews() {
		binding.btStartService.setOnClickListener(__ -> startCameraActivity());
	}

	private void startCameraActivity() {
		startActivity(CameraActivity.getLaunchIntent(getApplicationContext()));
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}
}