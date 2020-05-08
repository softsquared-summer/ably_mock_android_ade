package com.jinwoo.ably.src.main.fragments.home.children.today;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.fragments.home.children.today.adapter.ProductRecyclerAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.today.adapter.BannerSlideAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.today.data.Product;
import com.jinwoo.ably.src.main.fragments.home.children.today.interfaces.TodayFragmentView;
import com.jinwoo.ably.src.main.fragments.home.children.today.models.BannerResponse;
import com.jinwoo.ably.src.main.fragments.home.children.today.models.RecommendationResponse;
import java.util.ArrayList;
import static com.jinwoo.ably.src.ApplicationClass.USER_NAME;
import static com.jinwoo.ably.src.ApplicationClass.sSharedPreferences;

public class FragmentToday extends Fragment implements TodayFragmentView {

    private ProgressDialog mProgressDialog;
    private ImageView mTopAd;
    private ViewPager2 mBannerSlider;
    private TextView mPages, mRecommendationsForUser;
    private NestedScrollView mScrollView;
    private RecyclerView mRecommendations;
    private ProductRecyclerAdapter mProductRecyclerAdapter;
    private BannerSlideAdapter mBannerSlideAdapter;
    private ArrayList<Product> mProducts;
    private ArrayList<String> mBanners;
    private Handler mSlideHandler;
    private TodayService mTodayService;
    private int mPage;
    private GridLayoutManager layoutManager;

    public FragmentToday() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_main_home_today, container, false);
        mTodayService = new TodayService(this);
        mProducts = new ArrayList<>();
        mapWidgets(view);

        mTopAd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        // Slide banner image mapping
        tryGetBanners();

        // Slide banner auto scrolling
        mSlideHandler = new Handler();
        mBannerSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                mSlideHandler.removeCallbacks(slideRunnable);
                mSlideHandler.postDelayed(slideRunnable, 3000);

                String currentPage = (mBannerSlider.getCurrentItem() + 1) + "/" + mBanners.size();
                mPages.setText(currentPage);
            }
        });

        // Show user's name if logged in
        String userName = sSharedPreferences.getString(USER_NAME, null);
        if (userName != null) {
            String text = userName + "님을 위한 추천";
            mRecommendationsForUser.setText(text);
        }
        else {
            mRecommendationsForUser.setText("회원님을 위한 추천");
        }

        // Item recommendations mapping
        mPage = 1;

        tryGetRecommendations(mPage);

        // Paging mechanism
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    boolean isAtLast = !mScrollView.canScrollVertically(1);
                    if (isAtLast) {
                        tryGetRecommendations(++mPage);
                    }
                }
            });
        }

        return view;
    }

    private void mapWidgets(View view) {
        mTopAd =                    view.findViewById(R.id.today_iv_banner1);
        mBannerSlider =             view.findViewById(R.id.today_banner2);
        mPages =                    view.findViewById(R.id.today_tv_banner_pages);
        mRecommendationsForUser =   view.findViewById(R.id.today_tv_recommendations_for_user);
        mRecommendations =          view.findViewById(R.id.today_body);
        mScrollView                = view.findViewById(R.id.today_scrollview);
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            mBannerSlider.setCurrentItem(mBannerSlider.getCurrentItem() + 1);

            // If current page is the last, set it to 0 after 3 seconds
            if (mBannerSlider.getCurrentItem() == mBanners.size() - 1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBannerSlider.setCurrentItem(0);
                    }
                },3000);
            }
        }
    };

    private void tryGetBanners() {
        mTodayService.getBanners();
    }

    private void tryGetRecommendations(int page) {
        showProgressDialog();
        mTodayService.getRecommendations(page);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSlideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSlideHandler.postDelayed(slideRunnable, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    @Override
    public void validateBannerSuccess(BannerResponse bannerResponse) {
        hideProgressDialog();
        mBanners = new ArrayList<>();
        int code = bannerResponse.getCode();

        if (code == 100) {
            ArrayList<BannerResponse.Result> result = bannerResponse.getResult();

            for (int i = 0; i < result.size(); i++) {
                String url = result.get(i).getBannerUrl();
                mBanners.add(url);
            }

            mBannerSlideAdapter = new BannerSlideAdapter(getContext(), mBanners);
            mBannerSlider.setAdapter(mBannerSlideAdapter);
        }
    }

    @Override
    public void validateRecommendationSuccess(RecommendationResponse recommendationResponse) {
        hideProgressDialog();
        int code = recommendationResponse.getCode();

        if (code == 100) {
            ArrayList<RecommendationResponse.Result> recommendations = recommendationResponse.getResult();

            for (int i = 0; i < recommendations.size(); i++) {
                int productIdx =        recommendations.get(i).getProductIdx();
                String thumbnailUrl =   recommendations.get(i).getThumbnailUrl();
                String discountRatio =  recommendations.get(i).getDiscountRatio();
                String displayedPrice = recommendations.get(i).getDisplayedPrice();
                int marketIdx =         recommendations.get(i).getMarketIdx();
                String marketName =     recommendations.get(i).getMarketName();
                String productName =    recommendations.get(i).getProductName();
                String purchaseCnt =    recommendations.get(i).getPurchaseCnt();
                String isMyHeart =      recommendations.get(i).getIsMyHeart();
                String isHotDeal =      recommendations.get(i).getIsHotDeal();
                String isNew =          recommendations.get(i).getIsNew();

                Product product = new Product(productIdx, thumbnailUrl, discountRatio, displayedPrice, marketIdx, marketName, productName, purchaseCnt, isMyHeart, isHotDeal, isNew);
                mProducts.add(product);
            }

            mRecommendations.setHasFixedSize(true);
            mProductRecyclerAdapter = new ProductRecyclerAdapter(mProducts, getActivity());
            layoutManager = new GridLayoutManager(getActivity(), 2);
            mRecommendations.setLayoutManager(layoutManager);
            mRecommendations.setAdapter(mProductRecyclerAdapter);
        }
    }

    @Override
    public void validateFailure(String message) {}

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}