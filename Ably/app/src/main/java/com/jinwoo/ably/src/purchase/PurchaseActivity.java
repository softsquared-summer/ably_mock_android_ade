package com.jinwoo.ably.src.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;

public class PurchaseActivity extends BaseActivity {

    private ImageView mBack;
    private Button mPay;
    private ExpandableRelativeLayout mExpandableProducts;
    private String mFirstOption, mSecondOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        mapWidgets();

        Intent intent = getIntent();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapWidgets() {
        mBack               = findViewById(R.id.purchase_iv_back);
        mPay                = findViewById(R.id.purchase_btn_pay);
        mExpandableProducts = findViewById(R.id.purchase_expandable_products);
    }

    public void expandProducts(View view) {
        mExpandableProducts.toggle();
    }
}
