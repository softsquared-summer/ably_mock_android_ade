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
import java.util.ArrayList;

public class OptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> options;
    private LayoutInflater inflater;

    public OptionAdapter(@NonNull Context context, ArrayList<String> options) {
        this.context = context;
        this.options = options;
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

        return  convertView;
    }
}
