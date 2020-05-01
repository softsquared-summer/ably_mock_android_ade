package com.jinwoo.ably.src.main.fragments.home.children.newproducts;

import android.content.Context;
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
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.adapters.ProductRecyclerAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.adapters.ProductSlideAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.data.Product;
import java.util.ArrayList;

public class FragmentNew extends Fragment {

    private ImageView mTop;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private ProductSlideAdapter productSlideAdapter;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private ArrayList<Product> productList1, productList2;
    private Context context;

    public FragmentNew() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_main_home_new, container, false);

        mTop = view.findViewById(R.id.new_iv_banner);
        mViewPager = view.findViewById(R.id.new_body1);
        mRecyclerView = view.findViewById(R.id.new_body2);
        context = getActivity();

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Networking required
        //Inserting product data into productList
        ArrayList<Integer> photos = new ArrayList<>();
        photos.add(R.drawable.img_product_1);
        photos.add(R.drawable.img_product_2);
        photos.add(R.drawable.img_product_3);
        photos.add(R.drawable.img_product_4);
        productList1 = new ArrayList<>();
        productList2 = new ArrayList<>();

        // First product list setting
        productSlideAdapter = new ProductSlideAdapter(productList1, context);
        mViewPager.setAdapter(productSlideAdapter);

        // Second product list setting
        productRecyclerAdapter = new ProductRecyclerAdapter(productList2, context);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(productRecyclerAdapter);

        return view;
    }
}
