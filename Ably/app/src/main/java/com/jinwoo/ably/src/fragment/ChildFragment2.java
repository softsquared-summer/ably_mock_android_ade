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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.adapter.ProductAdapter;
import com.jinwoo.ably.src.data.Product;
import java.util.ArrayList;

public class ChildFragment2 extends Fragment {

    private ImageView mTop;
    private RecyclerView mRecyclerView1, mRecyclerView2;
    private ProductAdapter adapter1, adapter2;
    private ArrayList<Product> productList;

    public ChildFragment2() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_child2, container, false);

        mTop = (ImageView) view.findViewById(R.id.frag2_top);
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.frag2_body1);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.frag2_body2);
        productList = new ArrayList<>();

        mTop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ad click", Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: Networking required
        //Inserting product data into productList
        Product p1 = new Product(R.drawable.icon_my_page, 10000, "product p1", "This is first product", "No sales info");
        Product p2 = new Product(R.drawable.icon_my_page, 11000, "product p2", "This is second product", "No sales info");
        Product p3 = new Product(R.drawable.icon_my_page, 13000, "product p3", "This is third product", "No sales info");
        Product p4 = new Product(R.drawable.icon_my_page, 14000, "product p4", "This is fourth product", "No sales info");
        Product p5 = new Product(R.drawable.icon_my_page, 15000, "product p5", "This is fifth product", "No sales info");
        Product p6 = new Product(R.drawable.icon_my_page, 16000, "product p6", "This is sixth product", "No sales info");
        Product p7 = new Product(R.drawable.icon_my_page, 17000, "product p7", "This is seventh product", "No sales info");
        Product p8 = new Product(R.drawable.icon_my_page, 18000, "product p8", "This is eighth product", "No sales info");
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        productList.add(p6);
        productList.add(p7);
        productList.add(p8);

        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView2.setHasFixedSize(true);
        adapter1 = new ProductAdapter(productList);
        adapter2 = new ProductAdapter(productList);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL ,false));
        mRecyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView1.setAdapter(adapter1);
        mRecyclerView2.setAdapter(adapter2);

        return view;
    }
}
