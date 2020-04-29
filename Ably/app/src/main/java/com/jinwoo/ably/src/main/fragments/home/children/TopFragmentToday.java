package com.jinwoo.ably.src.main.fragments.home.children;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.adapter.ProductRecyclerAdapter;
import com.jinwoo.ably.src.main.adapter.BannerSlideAdapter;
import com.jinwoo.ably.src.main.data.Product;
import com.jinwoo.ably.src.main.data.SlideBanner;
import java.util.ArrayList;

public class TopFragmentToday extends Fragment {

    private ImageView mTop;
    private ViewPager2 mMid;
    private TextView mPages;
    private RecyclerView mRecyclerView;
    private ProductRecyclerAdapter mProductRecyclerAdapter;
    private BannerSlideAdapter mBannerSlideAdapter;
    private ArrayList<Product> productList;
    private ArrayList<SlideBanner> banners;
    private Handler mSlideHandler;

    public TopFragmentToday() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_top_today, container, false);

        mTop = view.findViewById(R.id.today_iv_banner1);
        mMid = view.findViewById(R.id.today_banner2);
        mPages = view.findViewById(R.id.today_tv_banner_pages);
        mRecyclerView = view.findViewById(R.id.today_body);

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        // Banner slider setting
        banners = new ArrayList<>();
        banners.add(new SlideBanner(R.drawable.img_banner_01));
        banners.add(new SlideBanner(R.drawable.img_banner_02));
        banners.add(new SlideBanner(R.drawable.img_banner_03));
        banners.add(new SlideBanner(R.drawable.img_banner_04));
        banners.add(new SlideBanner(R.drawable.img_banner_05));
        banners.add(new SlideBanner(R.drawable.img_banner_06));
        banners.add(new SlideBanner(R.drawable.img_banner_07));
        banners.add(new SlideBanner(R.drawable.img_banner_08));
        banners.add(new SlideBanner(R.drawable.img_banner_09));
        banners.add(new SlideBanner(R.drawable.img_banner_10));
        mBannerSlideAdapter = new BannerSlideAdapter(banners);
        mMid.setAdapter(mBannerSlideAdapter);

        // Slide banner auto scrolling
        mSlideHandler = new Handler();
        mMid.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                mSlideHandler.removeCallbacks(slideRunnable);
                mSlideHandler.postDelayed(slideRunnable, 3000);

                String currentPage = (mMid.getCurrentItem() + 1) + "/" + banners.size();
                mPages.setText(currentPage);
            }
        });

        //TODO: Networking required
        //Inserting product data into productList
        productList = new ArrayList<>();
        ArrayList<Integer> photos = new ArrayList<>();
        photos.add(R.drawable.img_product_1);
        photos.add(R.drawable.img_product_2);
        photos.add(R.drawable.img_product_3);
        photos.add(R.drawable.img_product_4);
        productList.add(new Product(R.raw.gif_product_1, photos, 10, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "NEW" ,"1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 0, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "HOT_DEAL", "1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 10, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "", "1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 10, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "NEW" ,"1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 0, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "HOT_DEAL", "1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 10, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "", "1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 10, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "NEW" ,"1,464개 구매중"));
        productList.add(new Product(R.raw.gif_product_1, photos, 0, 22800, "이너니티", "주문폭주/당일출구:-) 데일리 밑단컷팅 청스커트", "HOT_DEAL", "1,464개 구매중"));

        mRecyclerView.setHasFixedSize(true);
        mProductRecyclerAdapter = new ProductRecyclerAdapter(productList, getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mProductRecyclerAdapter);

        return view;
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            mMid.setCurrentItem(mMid.getCurrentItem() + 1);

            // If current page is the last, set it to 0 after 3 seconds
            if (mMid.getCurrentItem() == banners.size() - 1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMid.setCurrentItem(0);
                    }
                },3000);
            }
        }
    };

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
}