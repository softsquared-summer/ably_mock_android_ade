package com.jinwoo.ably.src.product;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.data.Product;
import com.jinwoo.ably.src.product.adapters.ImageSlideAdapter;
import com.jinwoo.ably.src.product.adapters.PagerAdapter;
import com.jinwoo.ably.src.product.fragments.options.OptionsFragment;
import com.jinwoo.ably.src.product.interfaces.ProductView;
import com.jinwoo.ably.src.product.models.ProductResponse;
import com.jinwoo.ably.src.product.views.WrapContentViewPager;
import com.jinwoo.ably.src.purchase.PurchaseActivity;

import java.util.ArrayList;
import me.relex.circleindicator.CircleIndicator3;

public class ProductActivity extends BaseActivity implements ProductView, OptionsFragment.OptionsListener {

    private ImageView               mBack;
    private ViewPager2              mImages;
    private CircleIndicator3        mCircleIndicator;
    private TextView                mName, mDiscount, mPrice, mOriginalPrice, mLikes, mCode,
                                    mMarket, mMarketTags;
    private TabLayout               mTabLayout;
    private WrapContentViewPager    mViewPager;
    private ImageView               mMarketThumbnail, mLike;
    private Button                  mPurchase;

    private int                     mMarketIdx, mProductIdx;
    private String                  mProductName, mDiscountRatio, mDisplayedPrice, mProductPrice,
                                    mProductCode, mContents, mIsMyHeart, mMarketName, mMarketHashTags,
                                    mMarketThumbnailUrl;
    private ArrayList<String>       mMainImgUrlList, mNormalImgUrlList;
    private ImageSlideAdapter       mImageSliderAdapter;
    private PagerAdapter            mPagerAdapter;
    private ProductService          mProductService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mapWidgets();
        mProductService = new ProductService(this);

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        mProductIdx = intent.getIntExtra("PRODUCT_INDEX", 0);
        tryGetProduct(mProductIdx);

        // Likes
        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsMyHeart.equals("N")) {
                    mLike.setImageResource(R.drawable.icon_full_heart_pink);
                    mIsMyHeart = "Y";
                }
                else {
                    mLike.setImageResource(R.drawable.icon_hollow_heart_pink);
                    mIsMyHeart = "N";
                }
            }
        });

        // Purchase
        mPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsFragment options = new OptionsFragment(mProductIdx);
                options.show(getSupportFragmentManager(), "options");
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
        mMarket             = findViewById(R.id.product_tv_market_name);
        mMarketTags         = findViewById(R.id.product_tv_market_hash_tags);
        mTabLayout          = findViewById(R.id.product_tab);
        mViewPager          = findViewById(R.id.product_fragment_container);
        mCode               = findViewById(R.id.product_tv_product_code);
        mMarketThumbnail    = findViewById(R.id.product_iv_market_thumbnail);
        mLike               = findViewById(R.id.product_iv_heart);
        mLikes              = findViewById(R.id.product_tv_likes);
        mPurchase           = findViewById(R.id.product_btn_purchase);
    }

    private void tryGetProduct(int productIdx) {
        showProgressDialog();
        mProductService.getProduct(productIdx);
    }

    @Override
    public void validateSuccess(ProductResponse response) {
        hideProgressDialog();

        int code = response.getCode();
        if (code == 100) {
            ProductResponse.Result result = response.getResult();

            mProductName        = result.getProductName();
            mDiscountRatio      = result.getDiscountRatio();
            mDisplayedPrice     = result.getDisplayedPrice();
            mProductPrice       = result.getPrice();
            mProductCode        = result.getProductCode();
            mContents           = result.getContents();
            mIsMyHeart          = result.getIsMyHeart();
            mMarketIdx          = result.getMarketIdx();
            mMarketName         = result.getMarketName();
            mMarketHashTags     = result.getMarketHashTags();
            mMarketThumbnailUrl = result.getMarketThumbnailUrl();
            mMainImgUrlList     = result.getMainImgUrlList();
            mNormalImgUrlList   = result.getNormalImgUrlList();

            // Product image setting
            mCircleIndicator.setViewPager(mImages);
            mCircleIndicator.createIndicators(mMainImgUrlList.size(), 0);
            mImageSliderAdapter = new ImageSlideAdapter(getApplicationContext(), mMainImgUrlList);
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

            // Product price info
            if (mDiscountRatio.equals("0%")) {
                mDiscount.setVisibility(View.INVISIBLE);
                mOriginalPrice.setVisibility(View.INVISIBLE);
                mPrice.setText(mProductPrice);
            }
            else {
                mDiscount.setText(mDiscountRatio);
                String displayedPrice = "         " + mDisplayedPrice;
                mOriginalPrice.setText(mProductPrice);
                mOriginalPrice.setPaintFlags(mOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                mPrice.setText(displayedPrice);
            }

            // Product code
            String productCode = "상품코드 " + mProductCode;
            mCode.setText(productCode);

            // Market info
            Glide.with(getApplicationContext()).load(mMarketThumbnailUrl).into(mMarketThumbnail);
            mMarket.setText(mMarketName);
            mMarketTags.setText(mMarketHashTags);;

            // Heart
            if (mIsMyHeart.equals("Y"))
                mLike.setImageResource(R.drawable.icon_full_heart_pink);
            else
                mLike.setImageResource(R.drawable.icon_hollow_heart_pink);

            // Tabs
            mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, 4, mNormalImgUrlList);
            mViewPager.setAdapter(mPagerAdapter);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
            mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) { mViewPager.setCurrentItem(tab.getPosition()); }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {}
                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });
        }
        else {
            showCustomToast(response.getMessage());
            finish();
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null ? String.valueOf(R.string.network_error) : message);
    }

    @Override
    public void onPurchaseClicked(String selectedFirstOption, String selectedSecondOption) {
        Intent intent = new Intent(ProductActivity.this, PurchaseActivity.class);
        intent.putExtra("FIRST_OPTION", selectedFirstOption);
        intent.putExtra("SECOND_OPTION", selectedSecondOption);
        startActivity(intent);
    }
}
