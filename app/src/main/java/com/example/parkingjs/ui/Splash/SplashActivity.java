package com.example.parkingjs.ui.Splash;

import android.os.Bundle;

import com.example.parkingjs.R;
import com.example.parkingjs.databinding.ActivitySplashBinding;
import com.example.parkingjs.ui.BaseActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}