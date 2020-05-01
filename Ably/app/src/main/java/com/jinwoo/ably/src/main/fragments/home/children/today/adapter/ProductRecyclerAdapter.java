package com.jinwoo.ably.src.main.fragments.home.children.today.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.fragments.home.children.today.data.Product;
import com.jinwoo.ably.src.product.ProductActivity;
import java.util.ArrayList;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.CustomViewHolder> {

    private ArrayList<Product> productList;
    private Context context;

    public ProductRecyclerAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        CustomViewHolder holder;
        holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        final Product product = productList.get(position);

        // Pull image from url
        Glide.with(context).load(product.getThumbnailUrl()).into(holder.photo);
        Log.d("URL", ""+product.getThumbnailUrl());

        holder.discount.setText(product.getDiscountRatio().equals("0%") ? null : product.getDiscountRatio() + " ");
        holder.price.setText(product.getDisplayedPrice());
        holder.market.setText(product.getMarketName());
        holder.name.setText(product.getProductName());

        if (product.getIsHotDeal().equals("Y")) {
            holder.tag.setImageResource(R.drawable.img_hotdeal);
            String purchaseCnt = "             " + product.getPurchaseCnt();
            holder.sales.setText(purchaseCnt);
        }
        else if (product.getIsNew().equals("Y")) {
            holder.tag.setImageResource(R.drawable.img_new);
            String purchaseCnt = "             " + product.getPurchaseCnt();
            holder.sales.setText(purchaseCnt);
        }
        else {
            holder.tag.setVisibility(View.INVISIBLE);
            holder.sales.setText(product.getPurchaseCnt());
        }

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getIsMyHeart().equals("N")) {
                    Glide.with(context).load(R.drawable.icon_full_heart_pink).into(holder.heart);
                    product.setIsMyHeart("Y");
                }
                else {
                    Glide.with(context).load(R.drawable.icon_hollow_heart_white).into(holder.heart);
                    product.setIsMyHeart("N");
                }
            }
        });

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "long click", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (productList != null ? productList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo, tag, heart;
        private TextView discount, price, market, name, sales;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.photo =    itemView.findViewById(R.id.item_product_iv_image);
            this.discount = itemView.findViewById(R.id.item_product_tv_discount);
            this.price =    itemView.findViewById(R.id.item_product_tv_price);
            this.market =   itemView.findViewById(R.id.item_product_tv_market);
            this.name =     itemView.findViewById(R.id.item_product_tv_name);
            this.tag =      itemView.findViewById(R.id.item_product_iv_tag);
            this.sales =    itemView.findViewById(R.id.item_product_tv_sales);
            this.heart =    itemView.findViewById(R.id.item_product_iv_heart);
        }
    }
}
