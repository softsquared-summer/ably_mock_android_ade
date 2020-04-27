package com.jinwoo.ably.src.fragment;

import android.os.Bundle;
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
import androidx.viewpager.widget.ViewPager;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.adapter.ProductAdapter;
import com.jinwoo.ably.src.adapter.SlideAdapter;
import com.jinwoo.ably.src.data.Product;
import com.jinwoo.ably.src.main.MainActivity;

import java.util.ArrayList;

public class ChildFragment1 extends Fragment {

    private ImageView mTop;
    private ViewPager mMid;
    private TextView mPages;
    private RecyclerView mRecyclerView;
    private ProductAdapter productAdapter;
    private SlideAdapter slideAdapter;
    private ArrayList<Product> productList;

    public ChildFragment1() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_child_1, container, false);

        mTop = (ImageView) view.findViewById(R.id.child1_top);
        mMid = (ViewPager) view.findViewById(R.id.child1_mid);
        mPages = (TextView) view.findViewById(R.id.child1_tv_banner_pages);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.child1_body);
        productList = new ArrayList<>();

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        slideAdapter = new SlideAdapter(getActivity());
        mMid.setAdapter(slideAdapter);
        mPages.setText(mMid.getCurrentItem() + "/" + slideAdapter.getCount());

        //TODO: Networking required
        //Inserting product data into productList
        Product p1 = new Product(R.drawable.img_product, 10000, "product p1", "This is first product", "No sales info");
        Product p2 = new Product(R.drawable.img_product, 11000, "product p2", "This is second product", "No sales info");
        Product p3 = new Product(R.drawable.img_product, 13000, "product p3", "This is third product", "No sales info");
        Product p4 = new Product(R.drawable.img_product, 14000, "product p4", "This is fourth product", "No sales info");
        Product p5 = new Product(R.drawable.img_product, 15000, "product p5", "This is fifth product", "No sales info");
        Product p6 = new Product(R.drawable.img_product, 16000, "product p6", "This is sixth product", "No sales info");
        Product p7 = new Product(R.drawable.img_product, 17000, "product p7", "This is seventh product", "No sales info");
        Product p8 = new Product(R.drawable.img_product, 18000, "product p8", "This is eighth product", "No sales info");
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        productList.add(p6);
        productList.add(p7);
        productList.add(p8);

        mRecyclerView.setHasFixedSize(true);
        productAdapter = new ProductAdapter(productList, (MainActivity)getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(productAdapter);

        return view;
    }
}