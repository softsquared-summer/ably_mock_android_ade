package com.jinwoo.ably.src.purchase.activities.chooseaddress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.AddressListFragment;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.data.Address;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.newaddress.NewAddressFragment;
import com.jinwoo.ably.src.purchase.PurchaseActivity;
import java.util.ArrayList;

public class ChooseAddressActivity extends BaseActivity {

    private TextView mTitle, mAdd;
    private ImageView mBack;
    private Button mOk;
    private FragmentTransaction mFragmentTransaction;
    private String mCurrentFragment;
    private ArrayList<Address> mAddressList;
    private NewAddressFragment mNewAddressFragment;
    private AddressListFragment mAddressListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        mapWidgets();
        mAddressList = new ArrayList<>();
        mNewAddressFragment     = new NewAddressFragment();
        mAddressListFragment    = new AddressListFragment(mAddressList);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFragment.equals("LIST")) {
                    finish();
                }
                else {
                   setListMode();
                }
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setAddMode();
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFragment.equals("LIST")) {
                    Intent intent = new Intent(ChooseAddressActivity.this, PurchaseActivity.class);
                    Address defaultAddress = mAddressListFragment.getDefaultAddress();

                    if (defaultAddress != null) {
                        String receiver     = defaultAddress.getReceiver();
                        String postalCode   = defaultAddress.getPostalCode();
                        String address      = defaultAddress.getAddress();
                        String detail       = defaultAddress.getDetail();
                        String contact      = defaultAddress.getContact();

                        intent.putExtra("HAS_ADDRESS", true);
                        intent.putExtra("RECEIVER", receiver);
                        intent.putExtra("POSTAL_CODE", postalCode);
                        intent.putExtra("ADDRESS", address);
                        intent.putExtra("DETAIL", detail);
                        intent.putExtra("CONTACT", contact);
                    }
                    else {
                        intent.putExtra("HAS_ADDRESS", false);
                    }

                    setResult(2, intent);
                    finish();
                }
                else {
                    if (mNewAddressFragment.isGood()) {
                        Address newAddress = mNewAddressFragment.getAddress();
                        mAddressList.add(newAddress);
                        setListMode();
                    }
                    else {
                        showCustomToast("정보를 모두 입력해 주십시오");
                    }
                }
            }
        });

        setListMode();
    }

    private void mapWidgets() {
        mBack   = findViewById(R.id.choose_address_iv_back);
        mTitle  = findViewById(R.id.choose_address_tv_title);
        mAdd    = findViewById(R.id.choose_address_tv_add_address);
        mOk     = findViewById(R.id.choose_address_btn_ok);
    }

    private void changeFragment(Fragment fragment) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.choose_address_fragment_container, fragment);
        mFragmentTransaction.commit();
    }

    private void setListMode() {
        mAdd.setVisibility(View.VISIBLE);
        mTitle.setText("배송지 변경");
        mOk.setText("배송지 선택 완료");
        mOk.setTextColor(getResources().getColor(R.color.colorWhite));
        mOk.setBackgroundResource(R.drawable.button_orange);
        changeFragment(mAddressListFragment);
        mCurrentFragment = "LIST";
    }

    private void setAddMode() {
        mAdd.setVisibility(View.INVISIBLE);
        mTitle.setText("배송지 추가");
        mOk.setText("배송지 입력하기");
        mOk.setTextColor(getResources().getColor(R.color.colorGrey));
        mOk.setBackgroundResource(R.drawable.button_light_grey);
        mNewAddressFragment = new NewAddressFragment();
        changeFragment(mNewAddressFragment);
        mCurrentFragment = "NEW";
    }
}
