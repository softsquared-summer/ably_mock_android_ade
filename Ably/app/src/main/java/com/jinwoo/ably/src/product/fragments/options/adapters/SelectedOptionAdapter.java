package com.jinwoo.ably.src.product.fragments.options.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.product.fragments.options.data.SelectedOption;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SelectedOptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SelectedOption> items;
    private View summaryView;
    private TextView totalCountView, totalCostView;
    private Button cartButton, purchaseButton;
    private LayoutInflater inflater;
    private NumberFormat format;

    public SelectedOptionAdapter(Context context,
                                 ArrayList<SelectedOption> items,
                                 View summaryView,
                                 TextView totalCountView,
                                 TextView totalCostView,
                                 Button cartButton,
                                 Button purchaseButton) {
        this.context        = context;
        this.items          = items;
        this.summaryView    = summaryView;
        this.totalCountView = totalCountView;
        this.totalCostView  = totalCostView;
        this.cartButton     = cartButton;
        this.purchaseButton = purchaseButton;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        format = NumberFormat.getInstance();
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
        View view = inflater.inflate(R.layout.item_selected_option, parent, false);
        SelectedOption item = items.get(position);

        TextView option1    = view.findViewById(R.id.selected_option_tv_option1);
        TextView option2    = view.findViewById(R.id.selected_option_tv_option2);
        TextView count      = view.findViewById(R.id.selected_option_tv_count);
        TextView cost       = view.findViewById(R.id.selected_option_tv_cost);
        Button minus        = view.findViewById(R.id.selected_option_btn_minus);
        Button plus         = view.findViewById(R.id.selected_option_btn_plus);
        Button exit         = view.findViewById(R.id.selected_option_btn_exit);

        option1 .setText(item.getOption1());
        option2 .setText(item.getOption2());
        count   .setText(String.valueOf(item.getCount()));
        String displayedPrice = format.format(item.getCount() * item.getCost()) + "원";
        cost    .setText(displayedPrice);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getCount() != 1) {
                    item.setCount(item.getCount() - 1);
                    count   .setText(String.valueOf(item.getCount()));
                    String displayedPrice = format.format(item.getCount() * item.getCost()) + "원";
                    cost    .setText(displayedPrice);
                    setSummary();
                    setButtons();
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCount(item.getCount() + 1);
                count   .setText(String.valueOf(item.getCount()));
                String displayedPrice = format.format(item.getCount() * item.getCost()) + "원";
                cost    .setText(displayedPrice);
                setSummary();
                setButtons();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(item);
                if (items.isEmpty())
                    summaryView.setVisibility(View.INVISIBLE);
                setSummary();
                setButtons();
                notifyDataSetChanged();
            }
        });

        setSummary();
        setButtons();
        return view;
    }

    public int getTotalCount() {
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            count += items.get(i).getCount();
        }
        return count;
    }

    public int getTotalCost() {
        int sum = 0;
        for (int i = 0; i < items.size(); i++) {
            sum += items.get(i).getCost() * items.get(i).getCount();
        }
        return sum;
    }

    private void setSummary() {
        totalCountView.setText(String.valueOf(getTotalCount()));
        NumberFormat format = NumberFormat.getInstance();
        String displayedTotalCost = format.format(getTotalCost()) + "원";
        totalCostView.setText(displayedTotalCost);
    }

    private void setButtons() {
        if (items.isEmpty()) {
            cartButton      .setBackgroundResource(R.drawable.button_light_grey);
            purchaseButton  .setBackgroundResource(R.drawable.button_light_grey);
            cartButton      .setEnabled(false);
            purchaseButton  .setEnabled(false);
        }
        else {
            cartButton      .setBackgroundResource(R.drawable.button_black);
            purchaseButton  .setBackgroundResource(R.drawable.button_black);
            cartButton      .setEnabled(true);
            purchaseButton  .setEnabled(true);
        }
    }
}
