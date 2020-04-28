package com.jinwoo.ably.src.main.fragments;

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
import com.jinwoo.ably.src.adapter.ProductRecyclerAdapter;
import com.jinwoo.ably.src.adapter.InfiniteSlideAdapter;
import com.jinwoo.ably.src.data.Product;
import com.jinwoo.ably.src.data.SlideBanner;
import com.jinwoo.ably.src.main.MainActivity;
import java.util.ArrayList;

public class TopFragment1 extends Fragment {

    private ImageView mTop;
    private ViewPager2 mMid;
    private TextView mPages;
    private RecyclerView mRecyclerView;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private InfiniteSlideAdapter infiniteSlideAdapter;
    private ArrayList<Product> productList;
    private Handler slideHandler;

    public TopFragment1() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_top_1, container, false);

        mTop = (ImageView) view.findViewById(R.id.top1_iv_banner1);
        mMid = (ViewPager2) view.findViewById(R.id.top1_banner2);
        mPages = (TextView) view.findViewById(R.id.top1_tv_banner_pages);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.top1_body);

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        // Banner slider setting
        ArrayList<SlideBanner> banners = new ArrayList<>();
        banners.add(new SlideBanner(R.drawable.img_banner1));
        banners.add(new SlideBanner(R.drawable.img_banner2));
        banners.add(new SlideBanner(R.drawable.img_banner3));
        banners.add(new SlideBanner(R.drawable.img_banner4));
        banners.add(new SlideBanner(R.drawable.img_banner5));
        infiniteSlideAdapter = new InfiniteSlideAdapter(mMid, banners);
        mMid.setAdapter(infiniteSlideAdapter);

        // Slide banner auto scrolling
        slideHandler = new Handler();
        mMid.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable, 3000);
            }
        });

        // Show current page
        mPages.setText(mMid.getCurrentItem() + "/" + banners.size());

        //TODO: Networking required
        //Inserting product data into productList
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.img_product, 10000, "product p1", "This is first product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 11000, "product p2", "This is second product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 13000, "product p3", "This is third product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 14000, "product p4", "This is fourth product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 15000, "product p5", "This is fifth product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 16000, "product p6", "This is sixth product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 17000, "product p7", "This is seventh product", "No sales info"));
        productList.add(new Product(R.drawable.img_product, 18000, "product p8", "This is eighth product", "No sales info"));


        mRecyclerView.setHasFixedSize(true);
        productRecyclerAdapter = new ProductRecyclerAdapter(productList, (MainActivity)getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(productRecyclerAdapter);

        return view;
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            mMid.setCurrentItem(mMid.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 3000);
    }
}