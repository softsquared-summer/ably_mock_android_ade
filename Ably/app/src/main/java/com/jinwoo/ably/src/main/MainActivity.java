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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.ApplicationClass;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.adapter.DrawerListAdapter;
import com.jinwoo.ably.src.main.fragments.home.FragmentHome;
import com.jinwoo.ably.src.main.fragments.style.FragmentStyle;
import com.jinwoo.ably.src.main.fragments.market.FragmentMarket;
import com.jinwoo.ably.src.main.fragments.pick.FragmentPick;
import com.jinwoo.ably.src.main.fragments.mypage.FragmentMyPage;
import com.jinwoo.ably.src.login.LoginActivity;
import com.jinwoo.ably.src.signup.SignUpHomeActivity;

public class MainActivity extends BaseActivity {
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
    private DrawerListAdapter mDrawerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapWidgets();

        // Initializing drawer listview
        mDrawerListAdapter = new DrawerListAdapter(getApplicationContext(), R.layout.item_category_parent, R.layout.item_category_child, ApplicationClass.getCategories());
        mDrawerListView.setAdapter(mDrawerListAdapter);

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
                    startActivity(new Intent(MainActivity.this, SignUpHomeActivity.class));
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
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.main_parent_container, new FragmentHome());
        mFragmentTransaction.commit();

        // Bottom navigation bar
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        mSearchBar.setVisibility(View.VISIBLE);
                        mMenuTitle.setVisibility(View.INVISIBLE);
                        changeFragment(new FragmentHome());
                        break;
                    case R.id.bottom_style:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("스타일");
                        changeFragment( new FragmentStyle());
                        break;
                    case R.id.bottom_market:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마켓");
                        changeFragment(new FragmentMarket());
                        break;
                    case R.id.bottom_favorite:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("찜");
                        changeFragment(new FragmentPick());
                        break;
                    case R.id.bottom_my_page:
                        mSearchBar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마이페이지");
                        changeFragment(new FragmentMyPage());
                        break;
                }
                return true;
            }
        });
    }

    private void mapWidgets() {
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