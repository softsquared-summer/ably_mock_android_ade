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
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.Product;
import com.jinwoo.ably.src.product.ProductActivity;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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

        Glide.with(context).asGif().load(product.getGif()).into(holder.photo);

        // Put null in discount if it is zero. Otherwise, add '% ' at the end of it.
        holder.discount.setText(product.getDiscount() == 0 ? null : product.getDiscount() + "% ");

        // Formatting price information into currency format
        int price = product.getPrice();
        String formattedPrice = NumberFormat.getInstance(Locale.getDefault()).format(price);
        holder.price.setText(formattedPrice);

        holder.name.setText(product.getMarket());
        holder.description.setText(product.getName());

        switch (product.getTag()) {
            case "NEW":
                holder.tag.setImageResource(R.drawable.img_new);
                String s1 = "             " + product.getSales();
                holder.sales.setText(s1);
                break;
            case "HOT_DEAL":
                holder.tag.setImageResource(R.drawable.img_hotdeal);
                String s2 = "             " + product.getSales();
                holder.sales.setText(s2);
                break;
            default:
                holder.tag.setVisibility(View.INVISIBLE);
                holder.sales.setText(product.getSales());
        }

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!product.isPicked()) {
                    Glide.with(context).load(R.drawable.icon_full_heart_pink).into(holder.heart);
                    product.setPicked(true);
                }
                else {
                    Glide.with(context).load(R.drawable.icon_hollow_heart_white).into(holder.heart);
                    product.setPicked(false);
                }
            }
        });

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                //TODO: put url info of the selected product in the intent
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
        private TextView discount, price, name, description, sales;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.photo = itemView.findViewById(R.id.item_product_iv_image);
            this.discount = itemView.findViewById(R.id.item_product_tv_discount);
            this.price = itemView.findViewById(R.id.item_product_tv_price);
            this.name = itemView.findViewById(R.id.item_product_tv_market);
            this.description = itemView.findViewById(R.id.item_product_tv_name);
            this.tag = itemView.findViewById(R.id.item_product_iv_tag);
            this.sales = itemView.findViewById(R.id.item_product_tv_sales);
            this.heart = itemView.findViewById(R.id.item_product_iv_heart);
        }
    }
}
