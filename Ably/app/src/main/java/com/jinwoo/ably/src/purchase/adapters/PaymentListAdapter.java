package com.jinwoo.ably.src.purchase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jinwoo.ably.R;

public class PaymentListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int groupLayout;
    private int childLayout;
    private LayoutInflater inflater;
    private String payment;
    private TextView selectedPayment;

    public PaymentListAdapter(Context context, int groupLayout, int childLayout) {
        this.context = context;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        payment = "";
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) { return null; }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(groupLayout, parent, false);
        }

        selectedPayment = convertView.findViewById(R.id.payment_tv_selected_payment);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(childLayout, parent, false);
        }

        RadioGroup group        = convertView.findViewById(R.id.payment_radio_group);
        RadioButton toss        = convertView.findViewById(R.id.payment_rbt_toss);
        RadioButton card        = convertView.findViewById(R.id.payment_rbt_card);
        RadioButton deposit    = convertView.findViewById(R.id.payment_rbt_deposit);
        RadioButton phone      = convertView.findViewById(R.id.payment_rbt_phone);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.payment_rbt_toss:
                        payment = "TOSS";
                        card.setChecked(false);
                        deposit.setChecked(false);
                        phone.setChecked(false);
                        break;
                    case R.id.payment_rbt_card:
                        payment = "CARD";
                        toss.setChecked(false);
                        deposit.setChecked(false);
                        phone.setChecked(false);
                        break;
                    case R.id.payment_rbt_deposit:
                        payment = "DEPOSIT";
                        toss.setChecked(false);
                        card.setChecked(false);
                        phone.setChecked(false);
                        break;
                    case R.id.payment_rbt_phone:
                        payment = "PHONE";
                        toss.setChecked(false);
                        card.setChecked(false);
                        deposit.setChecked(false);
                        break;
                }
                notifyDataSetChanged();
            }
        });

        LinearLayout tossLayout = convertView.findViewById(R.id.payment_toss);
        LinearLayout cardLayout = convertView.findViewById(R.id.payment_card);
        LinearLayout depositLayout = convertView.findViewById(R.id.payment_deposit);
        LinearLayout phoneLayout = convertView.findViewById(R.id.payment_phone);

        tossLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toss.setChecked(true);
                payment = "TOSS";
                card.setChecked(false);
                deposit.setChecked(false);
                phone.setChecked(false);
            }
        });
        cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setChecked(true);
                payment = "CARD";
                toss.setChecked(false);
                deposit.setChecked(false);
                phone.setChecked(false);
            }
        });
        depositLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposit.setChecked(true);
                payment = "DEPOSIT";
                toss.setChecked(false);
                card.setChecked(false);
                phone.setChecked(false);
            }
        });
        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setChecked(true);
                toss.setChecked(false);
                card.setChecked(false);
                deposit.setChecked(false);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public String getPayment() {
        if (payment.equals(null))
            return null;
        else
            return payment;
    }

    public void setSelectedPayment(String payment) {
        selectedPayment.setText(payment);
    }

    public void hideSelectedPayment(boolean hide) {
        if (hide)
            selectedPayment.setVisibility(View.INVISIBLE);
        else
            selectedPayment.setVisibility(View.VISIBLE);
    }

}
