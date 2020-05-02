package com.jinwoo.ably.src.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.product.adapters.ImageSlideAdapter;
import com.jinwoo.ably.src.product.adapters.PagerAdapter;
import com.jinwoo.ably.src.product.fragments.options.OptionsFragment;
import com.jinwoo.ably.src.product.views.WrapContentViewPager;
import com.jinwoo.ably.src.purchase.PurchaseActivity;
import java.util.ArrayList;
import me.relex.circleindicator.CircleIndicator3;

public class ProductActivity extends BaseActivity {

    private ImageView mBack;
    private ViewPager2 mImages;
    private CircleIndicator3 mCircleIndicator;
    private TextView mName, mDiscount, mPrice, mOriginalPrice, mLikes;
    private TabLayout mTabLayout;
    private WrapContentViewPager mViewPager;
    private ImageView mLike;
    private Button mPurchase;
    private String mProductUrl, mProductName, mProductDiscount, mProductPrice, mProductOriginalPrice;
    private ArrayList<Integer> mProductImages;
    private ImageSlideAdapter mImageSliderAdapter;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mapWidgets();

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TODO: get product url from the intent and parse, map it onto the UI of this activity
        Intent intent = getIntent();
        mProductUrl = intent.getStringExtra("URL");
        parseJSON(mProductUrl);

        // Product image setting
        mCircleIndicator.setViewPager(mImages);
        mCircleIndicator.createIndicators(mProductImages.size(), 0);
        mImageSliderAdapter = new ImageSlideAdapter(mProductImages);
        mImages.setAdapter(mImageSliderAdapter);
        mImages.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mImages.setCurrentItem(position);
                    mCircleIndicator.animatePageSelected(position);
                }
            }
        });

        // Product name
        mName.setText(mProductName);

        // Product discount info
        if (mProductDiscount.equals("")) mDiscount.setVisibility(View.INVISIBLE);
        else mDiscount.setText(mProductDiscount);

        // Product price
        String displayPrice;
        if (mProductDiscount.equals("")) displayPrice = mProductPrice;
        else displayPrice = "         " + mProductPrice;
        mPrice.setText(displayPrice);

        // Product price before discount
        if (mProductOriginalPrice.equals("")) mOriginalPrice.setVisibility(View.INVISIBLE);
        else mOriginalPrice.setText(mProductOriginalPrice);

        // Tabs
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, 4);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Likes
        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Purchase
        mPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsFragment options = new OptionsFragment();
                options.show(getSupportFragmentManager(), "options");
                //startActivity(new Intent(ProductActivity.this, PurchaseActivity.class));
            }
        });
    }

    private void mapWidgets() {
        mBack               = findViewById(R.id.product_iv_back);
        mImages             = findViewById(R.id.product_image);
        mCircleIndicator    = findViewById(R.id.product_indicator);
        mName               = findViewById(R.id.product_tv_name);
        mDiscount           = findViewById(R.id.product_tv_discount);
        mPrice              = findViewById(R.id.product_tv_price);
        mOriginalPrice      = findViewById(R.id.product_tv_original_price);
        mTabLayout          = findViewById(R.id.product_tab);
        mViewPager          = findViewById(R.id.product_fragment_container);
        mLike               = findViewById(R.id.product_iv_heart);
        mLikes              = findViewById(R.id.product_tv_likes);
        mPurchase           = findViewById(R.id.product_btn_purchase);
    }

    private void parseJSON(String url) {
        // Temporary settings
        mProductImages = new ArrayList<>();
        mProductImages.add(R.drawable.img_product_1);
        mProductImages.add(R.drawable.img_product_2);
        mProductImages.add(R.drawable.img_product_3);
        mProductImages.add(R.drawable.img_product_4);
        mProductName = "주문폭주/당일출고:-) 데일리 밑단컷팅 청스커트";
        mProductDiscount = "";
        mProductPrice = "22,800원";
        mProductOriginalPrice = "";
    }
}
