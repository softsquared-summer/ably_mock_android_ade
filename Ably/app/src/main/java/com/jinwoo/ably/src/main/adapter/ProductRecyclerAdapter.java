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
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.Product;
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
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        holder.photo.setImageResource(productList.get(position).getPhoto());
        holder.price.setText(String.valueOf(productList.get(position).getPrice()));
        holder.name.setText(productList.get(position).getName());
        holder.description.setText(productList.get(position).getDescription());
        holder.sales.setText(productList.get(position).getSales());

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

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView photo;
        protected TextView price;
        protected TextView name;
        protected TextView description;
        protected TextView sales;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.photo = (ImageView) itemView.findViewById(R.id.item_product_iv_image);
            this.price = (TextView) itemView.findViewById(R.id.item_product_tv_price);
            this.name = (TextView) itemView.findViewById(R.id.item_product_tv_name);
            this.description = (TextView) itemView.findViewById(R.id.item_product_tv_description);
            this.sales = (TextView) itemView.findViewById(R.id.item_product_tv_sales);
        }
    }
}
