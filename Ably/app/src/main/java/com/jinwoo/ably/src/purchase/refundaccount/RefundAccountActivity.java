package com.jinwoo.ably.src.purchase.refundaccount;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.purchase.PurchaseActivity;

public class RefundAccountActivity extends BaseActivity {

    View mBanks;
    EditText mHolder, mAccount;
    TextView mSelectedBank;
    Button mOk;
    String mBank, mStringHolder, mStringAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_account);
        mapWidgets();

        final int[] selectedItem = {0};
        mBanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] banks = new String[] {"KB국민은행", "SC제일은행", "경남은행", "광주은행", "기업은행",
                                                    "농협", "대구은행", "부산은행", "산업은행", "새마을금고", "수협", "신한은행",
                                                    "신협", "외환은행", "우리은행", "우체국", "전북은행", "카카오뱅크", "케이뱅크",
                                                    "하나은행(서울은행)", "한국씨티은행(한미은행)", "제주은행"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(RefundAccountActivity.this);
                dialog  .setTitle("은행 선택")
                        .setSingleChoiceItems(
                                banks,
                                -1,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        selectedItem[0] = which;
                                    }
                                }
                        )
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mBank = "";
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (selectedItem[0] == -1) return;
                                mBank = banks[selectedItem[0]];
                                mSelectedBank.setText(mBank);
                                mSelectedBank.setTextColor(getResources().getColor(R.color.colorBlack));
                            }
                        });

                dialog.create();
                dialog.show();
            }
        });

        mHolder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringHolder   = mHolder.getText().toString();
                mStringAccount  = mAccount.getText().toString();

                if (mStringAccount.equals("") || mStringHolder.equals("")) {
                    mOk.setBackgroundResource(R.drawable.button_light_grey);
                    mOk.setTextColor(getResources().getColor(R.color.colorGrey));
                    mOk.setEnabled(false);
                }
                else {
                    mOk.setBackgroundResource(R.drawable.button_orange);
                    mOk.setTextColor(getResources().getColor(R.color.colorWhite));
                    mOk.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStringHolder   = mHolder.getText().toString();
                mStringAccount  = mAccount.getText().toString();

                if (mStringAccount.equals("") || mStringHolder.equals("")) {
                    mOk.setBackgroundResource(R.drawable.button_light_grey);
                    mOk.setTextColor(getResources().getColor(R.color.colorGrey));
                    mOk.setEnabled(false);
                }
                else {
                    mOk.setBackgroundResource(R.drawable.button_orange);
                    mOk.setTextColor(getResources().getColor(R.color.colorWhite));
                    mOk.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStringHolder   = mHolder.getText().toString();
                mStringAccount  = mAccount.getText().toString();

                Intent intent = new Intent(RefundAccountActivity.this, PurchaseActivity.class);
                intent.putExtra("BANK", mBank);
                intent.putExtra("HOLDER", mStringHolder);
                intent.putExtra("ACCOUNT", mStringAccount);
                setResult(1, intent);
                finish();
            }
        });
    }

    private void mapWidgets() {
        mBanks          = findViewById(R.id.refund_account_banks);
        mSelectedBank   = findViewById(R.id.refund_account_tv_bank);
        mHolder         = findViewById(R.id.refund_account_et_holder);
        mAccount        = findViewById(R.id.refund_account_et_account);
        mOk             = findViewById(R.id.refund_account_btn_ok);

        mOk.setBackgroundResource(R.drawable.button_light_grey);
        mOk.setEnabled(false);
    }
}
