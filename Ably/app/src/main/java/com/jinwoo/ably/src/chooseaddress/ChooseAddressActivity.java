package com.jinwoo.ably.src.chooseaddress;

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
import com.jinwoo.ably.src.chooseaddress.fragments.addresslist.AddressListFragment;
import com.jinwoo.ably.src.chooseaddress.fragments.newaddress.NewAddressFragment;
import com.jinwoo.ably.src.purchase.PurchaseActivity;

public class ChooseAddressActivity extends BaseActivity {

    private TextView mTitle, mAdd;
    private ImageView mBack;
    private Button mOk;
    private FragmentTransaction mFragmentTransaction;
    private String mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);
        mapWidgets();

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
                    // TODO: put address data into intent if there's any
                    setResult(2, intent);
                }
                else {
                    // TODO: save user input
                  setListMode();
                }
            }
        });

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.choose_address_fragment_container, new AddressListFragment());
        mCurrentFragment = "LIST";
        mFragmentTransaction.commit();
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
        mAdd.setVisibility(View.INVISIBLE);
        mTitle.setText("배송지 변경");
        mOk.setText("배송지 선택 완료");
        mOk.setTextColor(getResources().getColor(R.color.colorWhite));
        mOk.setBackgroundResource(R.drawable.button_orange);
        changeFragment(new AddressListFragment());
        mCurrentFragment = "LIST";
    }

    private void setAddMode() {
        mAdd.setVisibility(View.INVISIBLE);
        mTitle.setText("배송지 추가");
        mOk.setText("배송지 입력하기");
        mOk.setTextColor(getResources().getColor(R.color.colorGrey));
        mOk.setBackgroundResource(R.drawable.button_light_grey);
        changeFragment(new NewAddressFragment());
        mCurrentFragment = "NEW";
    }
}
