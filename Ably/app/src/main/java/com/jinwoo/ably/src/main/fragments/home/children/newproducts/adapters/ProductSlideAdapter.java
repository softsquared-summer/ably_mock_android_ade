package com.jinwoo.ably.src.main.fragments.home.children.newproducts.adapters;

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
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.data.Product;
import com.jinwoo.ably.src.product.ProductActivity;

import java.util.ArrayList;
import java.util.Objects;

public class ProductSlideAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Product> productList;
    private LayoutInflater inflater;
    private ImageView mProductImage, mTag, mHeart;
    private TextView mDiscount, mPrice, mMarketName, mProductName, mSales;

    public ProductSlideAdapter(ArrayList<Product> productList, Context context) {
        this.context = context;
        this.productList = productList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        final Product product = productList.get(position);

        if (context != null) {
            view = inflater.inflate(R.layout.item_product, container, false);
            mapWidgets(view);

            Glide.with(context).load(product.getThumbnailUrl()).into(mProductImage);
            mDiscount.setText(product.getDiscountRatio().equals("0%") ? null : product.getDiscountRatio() + " ");
            mPrice.setText(product.getDisplayedPrice());
            mMarketName.setText(product.getMarketName());
            mProductName.setText(product.getProductName());

            if (product.getIsHotDeal().equals("Y")) {
                mTag.setImageResource(R.drawable.img_hotdeal);
                if (!product.getPurchaseCnt().equals("0")) {
                    String purchaseCnt = "             " + product.getPurchaseCnt();
                    mSales.setText(purchaseCnt);
                }
                else {
                    mSales.setVisibility(View.INVISIBLE);
                }
            }
            else if (product.getIsNew().equals("Y")) {
                mTag.setImageResource(R.drawable.img_new);
                if (!product.getPurchaseCnt().equals("0")) {
                    String purchaseCnt = "             " + product.getPurchaseCnt();
                    mSales.setText(purchaseCnt);
                }
                else {
                    mSales.setVisibility(View.INVISIBLE);
                }
            }
            else {
                mTag.setVisibility(View.INVISIBLE);
                if (!product.getPurchaseCnt().equals("0")) {
                    mSales.setText(product.getPurchaseCnt());
                }
                else {
                    mSales.setVisibility(View.INVISIBLE);
                }
            }

            mHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (product.getIsMyHeart().equals("N")) {
                        Glide.with(context).load(R.drawable.icon_full_heart_pink).into(mHeart);
                        product.setIsMyHeart("Y");
                    }
                    else {
                        Glide.with(context).load(R.drawable.icon_hollow_heart_white).into(mHeart);
                        product.setIsMyHeart("N");
                    }
                }
            });

        }

        if (view != null) {

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("PRODUCT_INDEX",    product.getProductIdx());
                    intent.putExtra("THUMBNAIL_URL",    product.getThumbnailUrl());
                    intent.putExtra("DISCOUNT_RATIO",   product.getDiscountRatio());
                    intent.putExtra("DISPLAYED_PRICE",  product.getDisplayedPrice());
                    intent.putExtra("MARKET_INDEX",     product.getMarketIdx());
                    intent.putExtra("MARKET_NAME",      product.getMarketName());
                    intent.putExtra("PRODUCT_NAME",     product.getProductName());
                    intent.putExtra("PURCHASE_COUNT",   product.getPurchaseCnt());
                    intent.putExtra("IS_MY_HEART",      product.getIsMyHeart());
                    intent.putExtra("IS_HOT_DEAL",      product.getIsHotDeal());
                    intent.putExtra("IS_NEW",           product.getIsNew());
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

        }

        container.addView(view);
        return Objects.requireNonNull(view);
    }

    private void mapWidgets(View view) {
        mProductImage = view.findViewById(R.id.item_product_double_iv_image1);
        mDiscount = view.findViewById(R.id.item_product_tv_discount);
        mPrice = view.findViewById(R.id.item_product_tv_price);
        mMarketName = view.findViewById(R.id.item_product_tv_market);
        mProductName = view.findViewById(R.id.item_product_tv_name);
        mTag = view.findViewById(R.id.item_product_iv_tag);
        mSales = view.findViewById(R.id.item_product_tv_sales);
        mHeart = view.findViewById(R.id.item_product_iv_heart);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.5f;
    }
}
