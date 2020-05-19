package com.example.parkingjs.ui.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.UiThread;

import com.example.parkingjs.R;
import com.example.parkingjs.databinding.ActivitySplashBinding;
import com.example.parkingjs.ui.BaseActivity;
import com.example.parkingjs.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        splashAnimation();
    }

    @UiThread
    private void splashAnimation() {
        Animation imageAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_splash);
        binding.ivSplash.startAnimation(imageAnim);
        imageAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}