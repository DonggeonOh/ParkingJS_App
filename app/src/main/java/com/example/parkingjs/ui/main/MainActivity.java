package com.example.parkingjs.ui.main;

import android.os.Bundle;

import com.example.parkingjs.R;
import com.example.parkingjs.databinding.ActivityMainBinding;
import com.example.parkingjs.ui.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}