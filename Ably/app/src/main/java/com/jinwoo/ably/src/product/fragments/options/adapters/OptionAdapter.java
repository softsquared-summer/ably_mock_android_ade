package com.jinwoo.ably.src.product.fragments.options.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import java.text.NumberFormat;
import java.util.ArrayList;

public class OptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> options;
    private ArrayList<Integer> prices, stocks;
    private LayoutInflater inflater;

    public OptionAdapter(@NonNull Context context,
                         ArrayList<String> options,
                         ArrayList<Integer> prices,
                         ArrayList<Integer> stocks) {
        this.context = context;
        this.options = options;
        this.prices = prices;
        this.stocks = stocks;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String option = options.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinner_normal, parent, false);
        }

        TextView optionName = convertView.findViewById(R.id.item_spinner_normal_tv_item);
        optionName.setText(option);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        String option = options.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinner_dropdown, parent, false);
        }

        TextView itemName = convertView.findViewById(R.id.item_spinner_dropdown_tv_item);
        itemName.setText(option);

        TextView itemPrice = convertView.findViewById(R.id.item_spinner_dropdown_tv_price);
        if (position == 0 || prices == null) {
            itemPrice.setVisibility(View.INVISIBLE);
        }
        else {
            itemPrice.setVisibility(View.VISIBLE);
            NumberFormat format = NumberFormat.getInstance();
            String displayedPrice = format.format(prices.get(position)) + "원";
            itemPrice.setText(displayedPrice);
        }

        return  convertView;
    }
}
