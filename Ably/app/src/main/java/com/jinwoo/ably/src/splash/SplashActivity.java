package com.jinwoo.ably.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.MainActivity;

public class SplashActivity extends BaseActivity {
    private ImageView mIvLogo, mIvText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIvLogo = (ImageView) findViewById(R.id.splash_iv_logo);
        mIvText = (ImageView) findViewById(R.id.splash_iv_text);

        mIvText.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                mIvText.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }, 2000);
            }
        }, 1000);
    }
}
