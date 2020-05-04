package com.jinwoo.ably.src.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.purchase.adapters.CartItemAdapter;
import com.jinwoo.ably.src.purchase.data.CartItem;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

public class PurchaseActivity extends BaseActivity {

    private ImageView mBack;
    private Button mPay;
    private ExpandableLayout mExpandableProducts;
    private ListView mCartItemListView;
    private CartItemAdapter mCartItemAdapter;
    private ArrayList<CartItem> mCartItems;
    private ArrayList<String> mOption1List, mOption2List;
    private ArrayList<Integer> mCountList, mCostList;
    private int mProductIdx;
    private String  mProductName, mMarketName, mProductThumbnail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        mapWidgets();

        Intent intent       = getIntent();
        mOption1List        = intent.getStringArrayListExtra("OPTION_1");
        mOption2List        = intent.getStringArrayListExtra("OPTION_2");
        mCountList          = intent.getIntegerArrayListExtra("COUNT");
        mCostList           = intent.getIntegerArrayListExtra("COST");
        mProductIdx         = intent.getIntExtra("PRODUCT_INDEX", -1);
        mProductName        = intent.getStringExtra("PRODUCT_NAME");
        mMarketName         = intent.getStringExtra("MARKET_NAME");
        mProductThumbnail   = intent.getStringExtra("PRODUCT_THUMBNAIL");

        mCartItems = new ArrayList<>();
        for (int i = 0; i < mOption1List.size(); i++) {
            int count = mCountList.get(i);
            int cost = mCostList.get(i);
            String option1 = mOption1List.get(i);
            String option2 = mOption2List.get(i);

            CartItem item = new CartItem(mProductIdx, count, cost, mProductThumbnail, mProductName, mMarketName, option1, option2);
            mCartItems.add(item);
        }

        mCartItemAdapter = new CartItemAdapter(getApplicationContext(), mCartItems);
        mCartItemListView.setAdapter(mCartItemAdapter);

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
        mCartItemListView   = findViewById(R.id.purchase_lv_cart_items);
    }

    public void expandProducts(View view) {
        mExpandableProducts.toggle();
    }
}
