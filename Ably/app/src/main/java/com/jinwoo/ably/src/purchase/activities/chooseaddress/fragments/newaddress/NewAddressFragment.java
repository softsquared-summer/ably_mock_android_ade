package com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.newaddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.data.Address;

public class NewAddressFragment extends Fragment {

    private EditText receiverView;
    private EditText postalCodeView;
    private EditText addressView;
    private EditText detailView;
    private EditText contactView;
    private String receiver, postalCode, address, detail, contact;

    public NewAddressFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_address, container, false);

        receiverView = view.findViewById(R.id.new_address_et_receiver);
        postalCodeView = view.findViewById(R.id.new_address_et_postal_code);
        addressView = view.findViewById(R.id.new_address_et_address);
        detailView = view.findViewById(R.id.new_address_et_detail);
        contactView = view.findViewById(R.id.new_address_et_contact);

        receiver = postalCode = address = detail = contact = "";

        return view;
    }

    public boolean isGood() {
        receiver     = receiverView.getText().toString();
        postalCode = postalCodeView.getText().toString();
        address      = addressView.getText().toString();
        detail       = detailView.getText().toString();
        contact      = contactView.getText().toString();

        if (receiver    .equals("") ||
            postalCode.equals("") ||
            address     .equals("") ||
            detail      .equals("") ||
            contact     .equals("")) {
            return false;
        }

        return true;
    }

    public Address getAddress() {
        Address result = new Address(receiver, postalCode, address, detail, contact);
        return result;
    }
}
