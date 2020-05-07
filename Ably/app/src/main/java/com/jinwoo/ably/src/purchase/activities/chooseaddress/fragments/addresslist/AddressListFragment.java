package com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.adapters.AddressListAdapter;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.data.Address;
import java.util.ArrayList;

public class AddressListFragment extends Fragment {

    private ListView mAddressListView;
    private AddressListAdapter mAddressListAdapter;
    private ArrayList<Address> mAddressList;
    private Address mAddress;

    public AddressListFragment(ArrayList<Address> mAddressList) {
        this.mAddressList = mAddressList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);

        mAddressListAdapter = new AddressListAdapter(getContext(), mAddressList);
        mAddressListView = view.findViewById(R.id.address_list);
        mAddressListView.setAdapter(mAddressListAdapter);
        setListViewHeightBasedOnChildren(mAddressListView);

        return view;
    }

    public Address getDefaultAddress() {
        int position = mAddressListAdapter.getDefaultAddress();
        if (position == -1) return null;
        return mAddressList.get(position);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        if (mAddressListAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < mAddressListAdapter.getCount(); i++) {
            View listItem = mAddressListAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAddressListAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
