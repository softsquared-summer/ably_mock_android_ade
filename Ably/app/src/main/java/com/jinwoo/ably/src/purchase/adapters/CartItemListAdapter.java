package com.jinwoo.ably.src.purchase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.purchase.data.Cart;
import com.jinwoo.ably.src.purchase.data.CartItem;
import com.makeramen.roundedimageview.RoundedImageView;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CartItemListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int groupLayout;
    private int childLayout;
    private Cart cart;
    private LayoutInflater inflater;

    public CartItemListAdapter(Context context, int groupLayout, int childLayout, Cart cart) {
        this.context = context;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
        this.cart = cart;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cart.getCartItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return cart;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cart.getCartItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private int getItemCount() {
        int sum = 0;
        ArrayList<CartItem> items = cart.getCartItems();

        for (int i = 0; i < items.size(); i++) {
            int count = items.get(i).getCount();
            sum += count;
        }

        return sum;
    }

    private int getMarketCount(String marketName) {
        int sum = 0;
        ArrayList<CartItem> items = cart.getCartItems();

        for (int i = 0; i < items.size(); i++) {
            if (marketName.equals(items.get(i).getMarketName())) {
                sum++;
            }
        }

        return sum;
    }

    private boolean isTop(int childPosition) {
        if (childPosition == 0) return true;

        ArrayList<CartItem> items   = cart.getCartItems();
        String currentMarketName    = items.get(childPosition).getMarketName();
        String previousMarketName   = items.get(childPosition - 1).getMarketName();

        if (currentMarketName.equals(previousMarketName))
            return false;
        else
            return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(groupLayout, parent, false);
        }

        TextView itemCount = convertView.findViewById(R.id.cart_item_parent_tv_number);
        String itemCountText = "주문상품 총 " + getItemCount() + "개";
        itemCount.setText(itemCountText);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(childLayout, parent, false);
        }

        CartItem item = cart.getCartItems().get(childPosition);
        int count               = item.getCount();
        int cost                = item.getCost();
        String productThumbnail = item.getProductThumbnail();
        String productName      = item.getProductName();
        String marketName       = item.getMarketName();
        String option1          = item.getOption1();
        String option2          = item.getOption2();

        String shipments = marketName + " 배송상품 " + getMarketCount(marketName) + "개";
        View divider = convertView.findViewById(R.id.cart_item_divider);
        TextView shipmentsView = convertView.findViewById(R.id.cart_item_child_tv_number_of_shipments);
        shipmentsView.setText(shipments);

        if (!isTop(childPosition)) {
            divider.setVisibility(View.VISIBLE);
            shipmentsView.setVisibility(View.INVISIBLE);
        }
        else {
            divider.setVisibility(View.INVISIBLE);
            shipmentsView.setVisibility(View.VISIBLE);
        }

        TextView marketNameView = convertView.findViewById(R.id.cart_item_child_tv_market);
        marketNameView.setText(marketName);

        TextView productNameView = convertView.findViewById(R.id.cart_item_child_tv_product_name);
        productNameView.setText(productName);

        String options = option1 + " | " + option2;
        TextView optionsView = convertView.findViewById(R.id.cart_item_child_tv_options);
        optionsView.setText(options);

        NumberFormat format = NumberFormat.getInstance();
        String displayedCost = format.format(cost) + "원";
        TextView costView = convertView.findViewById(R.id.cart_item_child_tv_cost);
        costView.setText(displayedCost);

        String displayedCount = "수량 " + count + "개";
        TextView countView = convertView.findViewById(R.id.cart_item_child_tv_count);
        countView.setText(displayedCount);

        RoundedImageView productImageView = convertView.findViewById(R.id.cart_item_child_iv_image);
        Glide.with(context).load(productThumbnail).into(productImageView);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
