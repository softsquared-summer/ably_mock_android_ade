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
import com.jinwoo.ably.src.main.adapter.DrawerListAdapter;
import com.jinwoo.ably.src.main.fragments.home.BottomFragmentHome;
import com.jinwoo.ably.src.main.fragments.style.BottomFragmentStyle;
import com.jinwoo.ably.src.main.fragments.market.BottomFragmentMarket;
import com.jinwoo.ably.src.main.fragments.pick.BottomFragmentPick;
import com.jinwoo.ably.src.main.fragments.mypage.BottomFragmentMyPage;
import com.jinwoo.ably.src.login.LoginActivity;
import com.jinwoo.ably.src.signup.SignUpActivity1;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private View mDrawerView;
    private BottomNavigationView mBottomNavigationView;
    private ImageView mDrawerMenuButton;
    private EditText mSearchBar;
    private LinearLayout mMenuTitle;
    private TextView mSeeMoreBenefits, mTitle;
    private Button mLogin, mSignIn, mPoint, mCoupon, mDelivery, mSupport;
    private FragmentTransaction mFragmentTransaction;
    private ExpandableListView mDrawerListView;
    private DrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapWidgets();

        // Initializing drawer listview
        adapter = new DrawerListAdapter(getApplicationContext(), R.layout.item_parent, R.layout.item_child, ApplicationClass.getCategories());
        mDrawerListView.setAdapter(adapter);

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
                v.performClick();
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

            mSignIn.setOnClickListener(new View.OnClickListener() {
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
        mSearchBar.setVisibility(View.VISIBLE);
        mMenuTitle.setVisibility(View.INVISIBLE);
        BottomFragmentHome frag = new BottomFragmentHome();
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.main_parent_container, frag);
        mFragmentTransaction.commit();

        // Bottom navigation bar
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        mSearchBar.setVisibility(View.VISIBLE);
                        mMenuTitle.setVisibility(View.INVISIBLE);
                        BottomFragmentHome fragment1 = new BottomFragmentHome();
                        changeFragment(fragment1);
                        break;
                    case R.id.bottom_style:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("스타일");
                        BottomFragmentStyle fragment2 = new BottomFragmentStyle();
                        changeFragment(fragment2);
                        break;
                    case R.id.bottom_market:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마켓");
                        BottomFragmentMarket fragment3 = new BottomFragmentMarket();
                        changeFragment(fragment3);
                        break;
                    case R.id.bottom_favorite:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("찜");
                        BottomFragmentPick fragment4 = new BottomFragmentPick();
                        changeFragment(fragment4);
                        break;
                    case R.id.bottom_my_page:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마이페이지");
                        BottomFragmentMyPage fragment5 = new BottomFragmentMyPage();
                        changeFragment(fragment5);
                        break;
                }
                return true;
            }
        });
    }

    private void mapWidgets() {
        mToolbar = findViewById(R.id.main_Toolbar);
        mDrawerLayout = findViewById(R.id.main_drawer_layout);
        mDrawerView = findViewById(R.id.drawer_drawer);
        mBottomNavigationView = findViewById(R.id.main_bottom_navigation);
        mDrawerMenuButton = findViewById(R.id.main_iv_menu);
        mSearchBar = findViewById(R.id.main_et_search_bar);
        mMenuTitle = findViewById(R.id.main_top_detail);
        mTitle = findViewById(R.id.main_top_detail_title);
        mSeeMoreBenefits = findViewById(R.id.drawer_tv_see_more_benefits);
        mLogin = findViewById(R.id.drawer_login);
        mSignIn = findViewById(R.id.drawer_signin);
        mPoint = findViewById(R.id.drawer_point);
        mCoupon = findViewById(R.id.drawer_coupon);
        mDelivery = findViewById(R.id.drawer_delivery);
        mSupport = findViewById(R.id.drawer_support);
        mDrawerListView = findViewById(R.id.drawer_listview);
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
}