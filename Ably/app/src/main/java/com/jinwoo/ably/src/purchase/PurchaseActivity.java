package com.jinwoo.ably.src.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.purchase.adapters.CartItemListAdapter;
import com.jinwoo.ably.src.purchase.adapters.PaymentListAdapter;
import com.jinwoo.ably.src.purchase.data.Cart;
import com.jinwoo.ably.src.purchase.data.CartItem;
import com.jinwoo.ably.src.purchase.refundaccount.RefundAccountActivity;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PurchaseActivity extends BaseActivity {

    private ImageView mBack;
    private Button mPay;
    private ScrollView mScrollView;
    private ExpandableListView mCartItemListView, mPaymentListView;
    private TextView mTotalCost, mChangeAccount, mAccountInfo;
    private CartItemListAdapter mCartItemListAdapter;
    private PaymentListAdapter mPaymentListAdapter;
    private ArrayList<CartItem> mCartItems;
    private ArrayList<String> mOption1List, mOption2List;
    private ArrayList<Integer> mCountList, mCostList;
    private int mProductIdx;
    private String  mProductName, mMarketName, mProductThumbnail, mBank, mRefundAccount, mRefundAccountHolder, mPayment;

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

        Cart cart = new Cart(mCartItems);
        mCartItemListAdapter = new CartItemListAdapter(getApplicationContext(), R.layout.item_cart_item_parent, R.layout.item_cart_item_child, cart);
        mCartItemListView.setAdapter(mCartItemListAdapter);

        // Dynamically adjust the listview height to make scrolling work
        mCartItemListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) mCartItemListView.getLayoutParams();
                int headerHeight    = mCartItemListView.getHeight();
                int itemHeight      = 150 * headerHeight / 100;
                int dividerHeight   = mCartItemListView.getDividerHeight();
                param.height        = (mCartItems.size() * (itemHeight + dividerHeight) + headerHeight + 50);
                mCartItemListView.setLayoutParams(param);
                mCartItemListView.refreshDrawableState();
                mScrollView.refreshDrawableState();
            }
        });
        mCartItemListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) mCartItemListView.getLayoutParams();
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                mCartItemListView.setLayoutParams(param);
                mCartItemListView.refreshDrawableState();
                mScrollView.refreshDrawableState();
            }
        });

        mChangeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PurchaseActivity.this, RefundAccountActivity.class), 1);
            }
        });

        mTotalCost.setText(getTotalCost());

        mPaymentListAdapter = new PaymentListAdapter(getApplicationContext(), R.layout.item_payment_parent, R.layout.item_payment_child);
        mPaymentListView.setAdapter(mPaymentListAdapter);
        mPaymentListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) mPaymentListView.getLayoutParams();
                int headerHeight    = mPaymentListView.getHeight();
                int itemHeight      = 283 * headerHeight / 100;
                param.height        = (headerHeight + itemHeight);
                mPaymentListView.setLayoutParams(param);
                mPaymentListView.refreshDrawableState();
                mScrollView.refreshDrawableState();

                mPaymentListAdapter.hideSelectedPayment(true);
            }
        });
        mPaymentListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) mPaymentListView.getLayoutParams();
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                mPaymentListView.setLayoutParams(param);
                mPaymentListView.refreshDrawableState();
                mScrollView.refreshDrawableState();

                mPayment = mPaymentListAdapter.getPayment();
                switch(mPayment) {
                    case "TOSS":
                        mPaymentListAdapter.setSelectedPayment("토스");
                        break;
                    case "CARD":
                        mPaymentListAdapter.setSelectedPayment("카드결제");
                        break;
                    case "DEPOSIT":
                        mPaymentListAdapter.setSelectedPayment("무통장입금");
                        break;
                    case "PHONE":
                        mPaymentListAdapter.setSelectedPayment("휴대폰 결제");
                        break;
                }
                mPaymentListAdapter.hideSelectedPayment(false);
            }
        });

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
        mCartItemListView   = findViewById(R.id.purchase_lv_cart_items);
        mScrollView         = findViewById(R.id.scrollView);
        mChangeAccount      = findViewById(R.id.purchase_tv_change_account);
        mAccountInfo        = findViewById(R.id.purchase_tv_account_info);
        mTotalCost          = findViewById(R.id.purchase_tv_total_cost);
        mPaymentListView    = findViewById(R.id.purchase_lv_payments);
    }

    private String getTotalCost() {
        int sum = 0;

        for (int i = 0; i < mCartItems.size(); i++) {
            int cost = mCostList.get(i) * mCountList.get(i);
            sum += cost;
        }

        NumberFormat format = NumberFormat.getInstance();
        String result = format.format(sum) + "원";

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                mBank                   = data.getStringExtra("BANK");
                mRefundAccountHolder    = data.getStringExtra("HOLDER");
                mRefundAccount          = data.getStringExtra("ACCOUNT");
                String accountInfo = mRefundAccountHolder + "  |  " + mBank + " " + mRefundAccount;
                mAccountInfo.setText(accountInfo);
                mAccountInfo.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
        }
    }
}
