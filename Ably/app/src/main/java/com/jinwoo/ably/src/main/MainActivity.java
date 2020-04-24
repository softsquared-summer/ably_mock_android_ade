package com.jinwoo.ably.src.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.interfaces.MainActivityView;
import com.jinwoo.ably.src.main.models.SignInResponse;

public class MainActivity extends BaseActivity implements MainActivityView {
    private MainService mMainService;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private View mDrawerView;
    private BottomNavigationView mBottomNavigationView, mTopNavigationView;
    private ImageView mDrawerMenuButton;
    private Button mLogin, mSignin, mPoint, mCoupon, mDelivery, mSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainService = new MainService(this);
        mToolbar = (Toolbar) findViewById(R.id.main_Toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerView = (View) findViewById(R.id.drawer_drawer);
        mTopNavigationView = (BottomNavigationView) findViewById(R.id.main_top_navigation);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_navigation);
        mDrawerMenuButton = (ImageView) findViewById(R.id.main_iv_menu);
        mLogin = (Button) findViewById(R.id.drawer_login);
        mSignin = (Button) findViewById(R.id.drawer_signin);
        mPoint = (Button) findViewById(R.id.drawer_point);
        mCoupon = (Button) findViewById(R.id.drawer_coupon);
        mDelivery = (Button) findViewById(R.id.drawer_delivery);
        mSupport = (Button) findViewById(R.id.drawer_support);

        setSupportActionBar(mToolbar);

        // Drawer navigation bar setting
        mDrawerMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerView);
            }
        });

        //mDrawerLayout.setDrawerListener(listener);
        mDrawerLayout.addDrawerListener(listener);
        mDrawerView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showCustomToast("TOUCH");
                return true;
            }
        });



        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCustomToast("LOG IN");
            }
        });

        mSignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCustomToast("SIGN IN");
            }
        });

        mPoint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCustomToast("POINT");
            }
        });

        mCoupon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCustomToast("COUPON");
            }
        });

        mDelivery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCustomToast("DELIVERY");
            }
        });

        mSupport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCustomToast("SUPPORT");
            }
        });


        // Top navigation Bar
        mTopNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.top_today:
                        showCustomToast("TODAY");
                        break;
                    case R.id.top_new:
                        showCustomToast("NEW");
                        break;
                    case R.id.top_best:
                        showCustomToast("BEST");
                        break;
                    case R.id.top_hot_deal:
                        showCustomToast("HOT DEAL");
                        break;
                }
                return true;
            }
        });

        // Bottom navigation bar
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mDrawerView))
            mDrawerLayout.closeDrawers();
        else
            finish();
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
