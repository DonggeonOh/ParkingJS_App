package com.example.parkingjs.ui.main;

import android.os.Bundle;

import com.example.parkingjs.R;
import com.example.parkingjs.databinding.ActivityMainBinding;
import com.example.parkingjs.ui.BaseActivity;
import com.example.parkingjs.ui.camera.CameraActivity;

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