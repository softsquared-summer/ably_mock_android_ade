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
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.Product;
import com.jinwoo.ably.src.product.ProductActivity;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ProductSlideAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Product> productList;
    private LayoutInflater inflater;
    private ImageView mProductImage, mTag, mHeart;
    private TextView mDiscount, mPrice, mName, mDescription, mSales;

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

        if (context != null) {
            view = inflater.inflate(R.layout.item_product, container, false);
            final Product product = productList.get(position);

            mProductImage = view.findViewById(R.id.item_product_iv_image);
            mDiscount = view.findViewById(R.id.item_product_tv_discount);
            mPrice = view.findViewById(R.id.item_product_tv_price);
            mName = view.findViewById(R.id.item_product_tv_market);
            mDescription = view.findViewById(R.id.item_product_tv_name);
            mTag = view.findViewById(R.id.item_product_iv_tag);
            mSales = view.findViewById(R.id.item_product_tv_sales);
            mHeart = view.findViewById(R.id.item_product_iv_heart);

            Glide.with(context).asGif().load(product.getGif()).into(mProductImage);

            // Put null in discount if it is zero. Otherwise, add '% ' at the end of it.
            mDiscount.setText(product.getDiscount() == 0 ? null : product.getDiscount() + "% ");

            // Formatting price information into currency format
            int price = product.getPrice();
            String formattedPrice = NumberFormat.getInstance(Locale.getDefault()).format(price);
            mPrice.setText(formattedPrice);

            mName.setText(product.getMarket());
            mDescription.setText(product.getName());

            switch (product.getTag()) {
                case "NEW":
                    mTag.setImageResource(R.drawable.img_new);
                    String s1 = "             " + product.getSales();
                    mSales.setText(s1);
                    break;
                case "HOT_DEAL":
                    mTag.setImageResource(R.drawable.img_hotdeal);
                    String s2 = "             " + product.getSales();
                    mSales.setText(s2);
                    break;
                default:
                    mTag.setVisibility(View.INVISIBLE);
                    mSales.setText(product.getSales());
            }

            mHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!product.isPicked()) {
                        Glide.with(context).load(R.drawable.icon_full_heart_pink).into(mHeart);
                        product.setPicked(true);
                    }
                    else {
                        Glide.with(context).load(R.drawable.icon_hollow_heart_white).into(mHeart);
                        product.setPicked(false);
                    }
                }
            });

        }

        if (view != null) {

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

        }

        container.addView(view);
        return Objects.requireNonNull(view);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
