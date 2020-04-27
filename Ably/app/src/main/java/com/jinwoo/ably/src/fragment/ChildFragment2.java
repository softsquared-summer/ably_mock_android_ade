package com.jinwoo.ably.src.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.jinwoo.ably.R;
import com.jinwoo.ably.src.adapter.ProductRecyclerAdapter;
import com.jinwoo.ably.src.adapter.ProductSlideAdapter;
import com.jinwoo.ably.src.data.Product;

import java.util.ArrayList;

public class ChildFragment2 extends Fragment {

    private ImageView mTop;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private ProductSlideAdapter productSlideAdapter;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private ArrayList<Product> productList1, productList2;

    public ChildFragment2() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_child_2, container, false);

        mTop = (ImageView) view.findViewById(R.id.child2_top);
        mViewPager = (ViewPager) view.findViewById(R.id.child2_body1);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.child2_body2);

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Networking required
        //Inserting product data into productList
        productList1 = new ArrayList<>();
        productList1.add(new Product(R.drawable.img_product, 10000, "best item 1", "This is first product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 11000, "best item 2", "This is second product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 13000, "best item 3", "This is third product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 14000, "best item 4", "This is fourth product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 15000, "best item 5", "This is fifth product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 16000, "best item 6", "This is sixth product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 17000, "best item 7", "This is seventh product", "No sales info"));
        productList1.add(new Product(R.drawable.img_product, 18000, "best item 8", "This is eighth product", "No sales info"));
        productList2 = new ArrayList<>();
        productList2.add(new Product(R.drawable.img_product, 10000, "new item 1", "This is first product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 11000, "new item 2", "This is second product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 13000, "new item 3", "This is third product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 14000, "new item 4", "This is fourth product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 15000, "new item 5", "This is fifth product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 16000, "new item 6", "This is sixth product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 17000, "new item 7", "This is seventh product", "No sales info"));
        productList2.add(new Product(R.drawable.img_product, 18000, "new item 8", "This is eighth product", "No sales info"));

        // First product list setting
        mViewPager.setClipToPadding(false);
        mViewPager.setClipChildren(false);
        mViewPager.setOffscreenPageLimit(3);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        mViewPager.setPadding(margin, 0, margin, 0);
        mViewPager.setPageMargin(margin / 2);

        for (int i = 0; i < productList1.size(); i++) {

        }

        productSlideAdapter = new ProductSlideAdapter(productList1, getActivity());
        mViewPager.setAdapter(productSlideAdapter);

        // Second product list setting
        productRecyclerAdapter = new ProductRecyclerAdapter(productList2, getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(productRecyclerAdapter);

        return view;
    }
}
