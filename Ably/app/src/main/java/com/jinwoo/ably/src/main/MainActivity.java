package com.jinwoo.ably.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.ApplicationClass;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.adapter.DrawerListAdapter;
import com.jinwoo.ably.src.main.fragments.BottomFragment1;
import com.jinwoo.ably.src.main.fragments.BottomFragment2;
import com.jinwoo.ably.src.main.fragments.BottomFragment3;
import com.jinwoo.ably.src.main.fragments.BottomFragment4;
import com.jinwoo.ably.src.main.fragments.BottomFragment5;
import com.jinwoo.ably.src.login.LoginActivity;
import com.jinwoo.ably.src.signup.SignUpActivity1;

public class MainActivity extends BaseActivity /*implements MainActivityView*/ {
    private MainService mMainService;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private View mDrawerView;
    private BottomNavigationView mBottomNavigationView;
    private ImageView mDrawerMenuButton;
    private EditText mSearchbar;
    private LinearLayout mMenuTitle;
    private TextView mSeeMoreBenefits, mTitle;
    private Button mLogin, mSignin, mPoint, mCoupon, mDelivery, mSupport;
    private FragmentTransaction mFragmentTransaction;
    private ExpandableListView mDrawerListview;
    private DrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mMainService = new MainService(this);
        mToolbar = (Toolbar) findViewById(R.id.main_Toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerView = (View) findViewById(R.id.drawer_drawer);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom_navigation);
        mDrawerMenuButton = (ImageView) findViewById(R.id.main_iv_menu);
        mSearchbar = (EditText) findViewById(R.id.main_et_search_bar);
        mMenuTitle = (LinearLayout) findViewById(R.id.main_top_detail);
        mTitle = (TextView) findViewById(R.id.main_top_detail_title);
        mSeeMoreBenefits = (TextView) findViewById(R.id.drawer_tv_see_more_benefits);
        mLogin = (Button) findViewById(R.id.drawer_login);
        mSignin = (Button) findViewById(R.id.drawer_signin);
        mPoint = (Button) findViewById(R.id.drawer_point);
        mCoupon = (Button) findViewById(R.id.drawer_coupon);
        mDelivery = (Button) findViewById(R.id.drawer_delivery);
        mSupport = (Button) findViewById(R.id.drawer_support);
        mDrawerListview = (ExpandableListView) findViewById(R.id.drawer_listview);

        // Initializing drawer listview
        adapter = new DrawerListAdapter(getApplicationContext(), R.layout.item_parent, R.layout.item_child, ApplicationClass.getCategories());
        mDrawerListview.setAdapter(adapter);

        setSupportActionBar(mToolbar);

        // Drawer navigation bar setting
        mDrawerMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerView);
            }
        });

        mDrawerLayout.addDrawerListener(listener);
        mDrawerView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        // Buttons in drawer menu
        {
            mSeeMoreBenefits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("SEE MORE BENEFITS");
                }
            });

            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

            mSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignUpActivity1.class));
                }
            });

            mPoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("POINT");
                }
            });

            mCoupon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("COUPON");
                }
            });

            mDelivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("DELIVERY");
                }
            });

            mSupport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("SUPPORT");
                }
            });
        }

        // Body fragment initial setting
        mSearchbar.setVisibility(View.VISIBLE);
        mMenuTitle.setVisibility(View.INVISIBLE);
        BottomFragment1 frag = new BottomFragment1();
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.main_parent_container, frag);
        mFragmentTransaction.commit();

        // Bottom navigation bar
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        mSearchbar.setVisibility(View.VISIBLE);
                        mMenuTitle.setVisibility(View.INVISIBLE);
                        BottomFragment1 fragment1 = new BottomFragment1();
                        changeFragment(fragment1);
                        break;
                    case R.id.bottom_style:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("스타일");
                        BottomFragment2 fragment2 = new BottomFragment2();
                        changeFragment(fragment2);
                        break;
                    case R.id.bottom_market:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마켓");
                        BottomFragment3 fragment3 = new BottomFragment3();
                        changeFragment(fragment3);
                        break;
                    case R.id.bottom_favorite:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("찜");
                        BottomFragment4 fragment4 = new BottomFragment4();
                        changeFragment(fragment4);
                        break;
                    case R.id.bottom_my_page:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마이페이지");
                        BottomFragment5 fragment5 = new BottomFragment5();
                        changeFragment(fragment5);
                        break;
                }
                return true;
            }
        });
    }

    private void changeFragment(Fragment fragment) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.main_parent_container, fragment);
        mFragmentTransaction.commit();
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}
        @Override
        public void onDrawerOpened(@NonNull View drawerView) {}
        @Override
        public void onDrawerClosed(@NonNull View drawerView) {}
        @Override
        public void onDrawerStateChanged(int newState) {}
    };

    @Override
    public void onBackPressed() {
        // When drawer menu is open, close it.
        if (mDrawerLayout.isDrawerOpen(mDrawerView))
            mDrawerLayout.closeDrawers();
        else
            finish();
    }
//
//    private void tryGetTest() {
//        showProgressDialog();
//        mMainService.getTest();
//    }
//
//    private void tryPostSignIn() {
//        showProgressDialog();
//        mMainService.postSignIn("id", "pw");
//    }
//
//    @Override
//    public void validateSuccess(String text) {
//        hideProgressDialog();
//    }
//
//    @Override
//    public void validateFailure(@Nullable String message) {
//        hideProgressDialog();
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
//    }
//
//    @Override
//    public void signInSuccess(SignInResponse.SignInResult signInResult) {
//        hideProgressDialog();
//        // What to do when sign in succeeds
//    }
}