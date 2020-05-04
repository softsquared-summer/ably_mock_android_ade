package com.jinwoo.ably.src.purchase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.purchase.data.CartItem;
import com.makeramen.roundedimageview.RoundedImageView;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CartItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CartItem> items;
    private LayoutInflater inflater;

    public CartItemAdapter(Context context, ArrayList<CartItem> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_cart_item, parent, false);
        CartItem item = items.get(position);

        int productIdx          = item.getProductIdx();
        int count               = item.getCount();
        int cost                = item.getCost();
        String productThumbnail = item.getProductThumbnail();
        String productName      = item.getProductName();
        String marketName       = item.getMarketName();
        String option1          = item.getOption1();
        String option2          = item.getOption2();

        String shipments = marketName + " 배송상품 " + getCount() + "개";
        TextView shipmentsView = view.findViewById(R.id.cart_item_tv_number_of_shipments);
        shipmentsView.setText(shipments);

        TextView marketNameView = view.findViewById(R.id.cart_item_tv_market);
        marketNameView.setText(marketName);

        TextView productNameView = view.findViewById(R.id.cart_item_tv_product_name);
        productNameView.setText(productName);

        String options = option1 + " | " + option2;
        TextView optionsView = view.findViewById(R.id.cart_item_tv_options);
        optionsView.setText(options);

        NumberFormat format = NumberFormat.getInstance();
        String displayedCost = format.format(cost) + "원";
        TextView costView = view.findViewById(R.id.cart_item_tv_cost);
        costView.setText(displayedCost);

        String displayedCount = "수량 " + count + "개";
        TextView countView = view.findViewById(R.id.cart_item_tv_count);
        countView.setText(displayedCount);

        RoundedImageView productImageView = view.findViewById(R.id.cart_item_iv_image);
        Glide.with(context).load(productThumbnail).into(productImageView);

        return view;
    }
}
