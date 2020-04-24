package com.jinwoo.ably.src.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.interfaces.MainActivityView;
import com.jinwoo.ably.src.main.models.SignInResponse;

public class MainActivity extends BaseActivity implements MainActivityView {
    private MainService mMainService;
    private Toolbar mToolbar;
    private BottomNavigationView mbottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainService = new MainService(this);
        mToolbar = (Toolbar) findViewById(R.id.main_Toolbar);
        mbottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        setSupportActionBar(mToolbar);

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        showCustomToast("HOME");
                        break;
                    case R.id.bottom_style:
                        showCustomToast("STYLE");
                        break;
                    case R.id.bottom_market:
                        showCustomToast("MARKET");
                        break;
                    case R.id.bottom_favorite:
                        showCustomToast("FAVORITE");
                        break;
                    case R.id.bottom_my_page:
                        showCustomToast("MY PAGE");
                        break;
                }
                return true;
            }
        });
    }

    private void tryGetTest() {
        showProgressDialog();
        mMainService.getTest();
    }

    private void tryPostSignIn() {
        showProgressDialog();
        mMainService.postSignIn("id", "pw");
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void signInSuccess(SignInResponse.SignInResult signInResult) {
        hideProgressDialog();
        // What to do when sign in succeeds
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            default:
                //tryGetTest();
                break;
        }
    }
}
