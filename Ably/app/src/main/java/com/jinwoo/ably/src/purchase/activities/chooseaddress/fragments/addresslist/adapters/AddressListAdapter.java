package com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.data.Address;
import java.util.ArrayList;

public class AddressListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Address> items;
    private LayoutInflater inflater;
    private int defaultAddress;

    public AddressListAdapter(Context context, ArrayList<Address> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        defaultAddress = -1;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_address, parent, false);
        }
        Address item = items.get(position);

        ImageView defaultAddressImg = convertView.findViewById(R.id.address_iv_default);
        if (item.isSelected()) {
            convertView.setBackgroundResource(R.drawable.button_blue);
            defaultAddressImg.setVisibility(View.VISIBLE);
        }
        else {
            convertView.setBackgroundResource(R.drawable.button_white);
            defaultAddressImg.setVisibility(View.INVISIBLE);
        }

        TextView receiver = convertView.findViewById(R.id.address_tv_receiver);
        receiver.setText(item.getReceiver());

        TextView address = convertView.findViewById(R.id.address_tv_address);
        address.setText(item.getAddress());

        TextView detail = convertView.findViewById(R.id.address_tv_detail);
        detail.setText(item.getDetail());

        TextView contact = convertView.findViewById(R.id.address_tv_contact);
        contact.setText(item.getContact());

        Button delete = convertView.findViewById(R.id.address_btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: double check deletion
                items.remove(item);
                if (position == getDefaultAddress()) {
                    setDefaultAddress(-1);
                }
                Toast.makeText(context, "주소가 삭제되었습니다", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        Button edit = convertView.findViewById(R.id.address_btn_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: edit address
                Toast.makeText(context, "Edit address", Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item.isSelected()) {
                    setDefaultAddress(position);
                    item.setSelected(true);
                    unselectOthers(position);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    private void unselectOthers(int selected) {
        for (int i = 0; i < items.size(); i++) {
            if (i != selected) {
                items.get(i).setSelected(false);
            }
        }
    }

    private void setDefaultAddress(int position) {
        defaultAddress = position;
    }

    public int getDefaultAddress() {
        return defaultAddress;
    }
}
