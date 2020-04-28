package com.jinwoo.ably.src.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.Product;
import com.jinwoo.ably.src.product.ProductActivity;
import java.util.ArrayList;

public class ProductSlideAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Product> itemList;
    private LayoutInflater inflater;
    private ImageView mProductImage;
    private TextView mPrice, mName, mDescription, mSales;

    public ProductSlideAdapter(ArrayList<Product> itemList, Context context) {
        this.context = context;
        this.itemList = itemList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        if (context != null) {
            view = inflater.inflate(R.layout.item_product, container, false);

            mProductImage = (ImageView) view.findViewById(R.id.item_product_iv_image);
            mPrice = (TextView) view.findViewById(R.id.item_product_tv_price);
            mName = (TextView) view.findViewById(R.id.item_product_tv_name);
            mDescription = (TextView) view.findViewById(R.id.item_product_tv_description);
            mSales = (TextView) view.findViewById(R.id.item_product_tv_sales);

            mProductImage.setImageResource(itemList.get(position).getPhoto());
            mPrice.setText(String.valueOf(itemList.get(position).getPrice()));
            mName.setText(itemList.get(position).getName());
            mDescription.setText(itemList.get(position).getDescription());
            mSales.setText(itemList.get(position).getSales());
        }

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                //TODO: put url info of the selected product in the intent
                context.startActivity(intent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "long click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
