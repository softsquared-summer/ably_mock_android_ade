package com.jinwoo.ably.src.main;

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
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.adapter.DrawerListAdapter;
import com.jinwoo.ably.src.data.Category;
import com.jinwoo.ably.src.fragment.ParentFragment1;
import com.jinwoo.ably.src.fragment.ParentFragment2;
import com.jinwoo.ably.src.fragment.ParentFragment3;
import com.jinwoo.ably.src.fragment.ParentFragment4;
import com.jinwoo.ably.src.fragment.ParentFragment5;
import com.jinwoo.ably.src.main.interfaces.MainActivityView;
import com.jinwoo.ably.src.main.models.SignInResponse;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainActivityView {
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
    private ArrayList<Category> categoryList;
    private DrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainService = new MainService(this);
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
        {
            categoryList = new ArrayList<Category>();
            Category upper = new Category("상의");
            upper.child.add("전체");
            upper.child.add("티셔츠");
            upper.child.add("슬리브리스");
            upper.child.add("블라우스/셔츠");
            upper.child.add("니트");
            upper.child.add("맨투맨/후드");
            upper.child.add("베스트");
            categoryList.add(upper);
            Category outer = new Category("아우터");
            outer.child.add("전체");
            outer.child.add("가디건");
            outer.child.add("자켓");
            outer.child.add("점퍼");
            outer.child.add("코트");
            outer.child.add("패딩");
            categoryList.add(outer);
            Category onePiece = new Category("원피스");
            onePiece.child.add("전체");
            onePiece.child.add("미니원피스");
            onePiece.child.add("롱원피스");
            onePiece.child.add("투피스");
            categoryList.add(onePiece);
            Category pants = new Category("팬츠");
            pants.child.add("전체");
            pants.child.add("롱팬츠");
            pants.child.add("숏팬츠");
            pants.child.add("슬랙스");
            pants.child.add("레깅스");
            pants.child.add("점프수트");
            categoryList.add(pants);
            Category skirt = new Category("스커트");
            skirt.child.add("전체");
            skirt.child.add("미니/미디 스커트");
            skirt.child.add("롱스커트");
            categoryList.add(skirt);
            Category bag = new Category("가방");
            bag.child.add("전체");
            bag.child.add("크로스백");
            bag.child.add("숄더백");
            bag.child.add("토트백");
            bag.child.add("클러치");
            bag.child.add("에코백");
            bag.child.add("백팩");
            categoryList.add(bag);
            Category shoes = new Category("신발");
            shoes.child.add("전체");
            shoes.child.add("플랫/로퍼");
            shoes.child.add("힐");
            shoes.child.add("스니커즈");
            shoes.child.add("샌들");
            shoes.child.add("슬리퍼/쪼리");
            shoes.child.add("워커/부츠");
            categoryList.add(shoes);
            Category props = new Category("패션소품");
            props.child.add("전체");
            props.child.add("모자/헤어");
            props.child.add("양말/스타킹");
            props.child.add("시계");
            props.child.add("머플러");
            props.child.add("폰 악세사리");
            props.child.add("기타");
            categoryList.add(props);
            Category jewelry = new Category("주얼리");
            jewelry.child.add("전체");
            jewelry.child.add("귀걸이");
            jewelry.child.add("목걸이");
            jewelry.child.add("반지");
            jewelry.child.add("팔찌/발찌");
            categoryList.add(jewelry);
            Category underwear = new Category("언더웨어");
            underwear.child.add("전체");
            underwear.child.add("브라팬티");
            underwear.child.add("보정");
            underwear.child.add("이너");
            underwear.child.add("홈웨어");
            categoryList.add(underwear);
            Category beachwear = new Category("비치웨어");
            beachwear.child.add("전체");
            beachwear.child.add("비키니");
            beachwear.child.add("래쉬가드");
            beachwear.child.add("악세사리");
            categoryList.add(beachwear);
            adapter = new DrawerListAdapter(getApplicationContext(), R.layout.item_parent, R.layout.item_child, categoryList);
            mDrawerListview.setAdapter(adapter);
        }

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
                    showCustomToast("LOG IN");
                }
            });

            mSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomToast("SIGN IN");
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
        ParentFragment1 frag = new ParentFragment1();
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
                        ParentFragment1 fragment1 = new ParentFragment1();
                        changeFragment(fragment1);
                        break;
                    case R.id.bottom_style:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("스타일");
                        ParentFragment2 fragment2 = new ParentFragment2();
                        changeFragment(fragment2);
                        break;
                    case R.id.bottom_market:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마켓");
                        ParentFragment3 fragment3 = new ParentFragment3();
                        changeFragment(fragment3);
                        break;
                    case R.id.bottom_favorite:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("찜");
                        ParentFragment4 fragment4 = new ParentFragment4();
                        changeFragment(fragment4);
                        break;
                    case R.id.bottom_my_page:
                        mSearchbar.setVisibility(View.INVISIBLE);
                        mMenuTitle.setVisibility(View.VISIBLE);
                        mTitle.setText("마이페이지");
                        ParentFragment5 fragment5 = new ParentFragment5();
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