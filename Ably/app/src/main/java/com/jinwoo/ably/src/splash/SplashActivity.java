package com.jinwoo.ably.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.MainActivity;
import com.jinwoo.ably.src.splash.interfaces.SplashActivityView;
import com.jinwoo.ably.src.splash.models.SplashResponse;

import static com.jinwoo.ably.src.ApplicationClass.USER_NAME;
import static com.jinwoo.ably.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.jinwoo.ably.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity implements SplashActivityView {

    private ImageView mIvText;
    private SplashService mSplashService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mapWidgets();
        mSplashService = new SplashService(this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                mIvText.setVisibility(View.VISIBLE);
                new Handler().postDelayed(beginSignIn, 1000);
            }
        }, 1000);
    }

    private void mapWidgets() {
        mIvText = (ImageView) findViewById(R.id.splash_iv_text);
        mIvText.setVisibility(View.INVISIBLE);
    }

    private Runnable beginSignIn = new Runnable() {
        @Override
        public void run() {
            trySignIn();
        }
    };

    private void trySignIn() {
        mSplashService.getSignIn();
    }

    @Override
    public void validateSuccess(SplashResponse response) {
        int code = response.getCode();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);

        if (code == 100) {
            showCustomToast(sSharedPreferences.getString(USER_NAME, null) + "님, 로그인 되었습니다");
            intent.putExtra("LOG_IN", true);
            startActivity(intent);
            finish();
        }
        else {
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void validateFailure(String message) {
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
        finish();
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
