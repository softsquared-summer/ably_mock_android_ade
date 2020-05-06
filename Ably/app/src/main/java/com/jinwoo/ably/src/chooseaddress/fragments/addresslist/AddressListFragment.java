package com.jinwoo.ably.src.chooseaddress.fragments.addresslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.chooseaddress.fragments.addresslist.adapters.AddressListAdapter;
import com.jinwoo.ably.src.chooseaddress.fragments.addresslist.data.Address;
import java.util.ArrayList;

public class AddressListFragment extends Fragment {

    private ListView mAddressListView;
    private AddressListAdapter mAddressListAdapter;
    private ArrayList<Address> mAddressList;

    public AddressListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_list, container, false);

        mAddressList = new ArrayList<>();
        mAddressListAdapter = new AddressListAdapter(getContext(), mAddressList);
        mAddressListView.setAdapter(mAddressListAdapter);

        return view;
    }
}
