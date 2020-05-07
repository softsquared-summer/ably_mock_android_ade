package com.jinwoo.ably.src.purchase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.purchase.activities.chooseaddress.ChooseAddressActivity;
import com.jinwoo.ably.src.purchase.activities.complete.PurchaseCompleteActivity;
import com.jinwoo.ably.src.purchase.adapters.CartItemListAdapter;
import com.jinwoo.ably.src.purchase.adapters.PaymentListAdapter;
import com.jinwoo.ably.src.purchase.data.Cart;
import com.jinwoo.ably.src.purchase.data.CartItem;
import com.jinwoo.ably.src.purchase.activities.refundaccount.RefundAccountActivity;
import com.jinwoo.ably.src.purchase.interfaces.PurchaseView;
import com.jinwoo.ably.src.purchase.models.PurchaseResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;

import okhttp3.RequestBody;

import static com.jinwoo.ably.src.ApplicationClass.MEDIA_TYPE_JSON;

public class PurchaseActivity extends BaseActivity implements PurchaseView {

    private ImageView mBack;
    private Button mOrder;
    private ScrollView mScrollView;
    private ExpandableListView mCartItemListView, mPaymentListView;
    private TextView mTotalCost, mEnterAddress, mDisplayedAddress, mSelectedMessage, mChangeAccount, mAccountInfo;
    private EditText mCustomMessage;
    private ConstraintLayout mMessageContainer;
    private CartItemListAdapter mCartItemListAdapter;
    private PaymentListAdapter mPaymentListAdapter;
    private ArrayList<CartItem> mCartItems;
    private ArrayList<String> mOption1List, mOption2List;
    private ArrayList<Integer> mIdxList, mCountList, mCostList;
    private String  mProductName, mMarketName, mProductThumbnail, mBank, mRefundAccount, mRefundAccountHolder, mPayment,
                    mReceiver, mPostalCode, mAddress, mDetail, mContact, mMessage;
    private PurchaseService mPurchaseService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        mapWidgets();
        mPurchaseService = new PurchaseService(this);

        Intent intent       = getIntent();
        mIdxList            = intent.getIntegerArrayListExtra("IDX");
        mOption1List        = intent.getStringArrayListExtra("OPTION_1");
        mOption2List        = intent.getStringArrayListExtra("OPTION_2");
        mCountList          = intent.getIntegerArrayListExtra("COUNT");
        mCostList           = intent.getIntegerArrayListExtra("COST");
        mProductName        = intent.getStringExtra("PRODUCT_NAME");
        mMarketName         = intent.getStringExtra("MARKET_NAME");
        mProductThumbnail   = intent.getStringExtra("PRODUCT_THUMBNAIL");

        mCartItems = new ArrayList<>();
        for (int i = 0; i < mOption1List.size(); i++) {
            int count = mCountList.get(i);
            int cost = mCostList.get(i);
            String option1 = mOption1List.get(i);
            String option2 = mOption2List.get(i);

            CartItem item = new CartItem(count, cost, mProductThumbnail, mProductName, mMarketName, option1, option2);
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

        mEnterAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PurchaseActivity.this, ChooseAddressActivity.class), 2);
            }
        });

        final int[] selectedItem = {0};
        mMessageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] messages = new String[] { "선택 안함", "부재시 경비실에 맡겨주세요.", "집앞에 놔주세요.", "택배함에 놔주세요",
                                                        "집으로 직접 배송해주세요.", "배송 전에 꼭 연락주세요.", "직접 입력" };

                AlertDialog.Builder dialog = new AlertDialog.Builder(PurchaseActivity.this);
                dialog  .setTitle("배송 시 요청사항")
                        .setSingleChoiceItems(messages, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedItem[0] = which;
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mMessage = null;
                                mSelectedMessage.setText("배송 시 요청사항을 선택해주세요.");
                                mSelectedMessage.setTextColor(getResources().getColor(R.color.colorGrey));
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (selectedItem[0] == -1) return;
                                mMessage = messages[selectedItem[0]];
                                mSelectedMessage.setText(mMessage);
                                mSelectedMessage.setTextColor(getResources().getColor(R.color.colorBlack));

                                if (mMessage.equals("직접 입력")) {
                                    mCustomMessage.setVisibility(View.VISIBLE);
                                }
                                else {
                                    mCustomMessage.setVisibility(View.GONE);
                                }
                            }
                        });

                dialog.create();
                dialog.show();
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

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject body = new JSONObject();
                    JSONArray products = new JSONArray();

                    for (int i = 0; i < mCountList.size(); i++) {
                        JSONObject product = new JSONObject();
                        product.put("detailedProductIdx", mIdxList.get(i));
                        product.put("number", mCountList.get(i));
                        products.put(product);
                    }

                    body.put("productInfo", products);
                    body.put("paymentType", mPayment);
                    body.put("refundBank", mBank);
                    body.put("refundOwner", mRefundAccountHolder);
                    body.put("refundAccount", mRefundAccount);
                    body.put("depositBank", "신한은행");
                    body.put("depositor", "황보진우");
                    body.put("cashReceipt", "NO");
                    body.put("receiver", mReceiver);
                    body.put("postalCode", mPostalCode);
                    body.put("address", mAddress);
                    body.put("detailedAddress", mDetail);
                    body.put("phone", mContact);
                    body.put("message", mMessage);

                    RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, body.toString());
                    Log.d("JSON:", body.toString());
                    tryPostOrder(requestBody);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void mapWidgets() {
        mBack               = findViewById(R.id.purchase_iv_back);
        mOrder = findViewById(R.id.purchase_btn_pay);
        mCartItemListView   = findViewById(R.id.purchase_lv_cart_items);
        mScrollView         = findViewById(R.id.scrollView);
        mEnterAddress       = findViewById(R.id.purchase_tv_enter_address);
        mDisplayedAddress   = findViewById(R.id.purchase_tv_type_in_address);
        mMessageContainer   = findViewById(R.id.purchase_message_container);
        mCustomMessage      = findViewById(R.id.purchase_et_custom_message);
        mSelectedMessage    = findViewById(R.id.purchase_tv_selected_message);
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
            case 2:
                boolean hasAddress = data.getBooleanExtra("HAS_ADDRESS", false);
                if (hasAddress) {
                    mReceiver     = data.getStringExtra("RECEIVER");
                    mPostalCode   = data.getStringExtra("POSTAL_CODE");
                    mAddress      = data.getStringExtra("ADDRESS");
                    mDetail       = data.getStringExtra("DETAIL");
                    mContact      = data.getStringExtra("CONTACT");

                    String displayedAddress = mReceiver + " " + mContact + "\n" + mAddress + "\n" + mDetail;
                    mDisplayedAddress.setText(displayedAddress);
                    mDisplayedAddress.setTextColor(getResources().getColor(R.color.colorBlack));

                    mMessageContainer.setVisibility(View.VISIBLE);
                }
                else {
                    mDisplayedAddress.setText("배송지를 입력해주세요.");
                    mDisplayedAddress.setTextColor(getResources().getColor(R.color.colorGrey));
                    mMessageContainer.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void tryPostOrder(RequestBody requestBody) {
        showProgressDialog();
        mPurchaseService.postOrder(requestBody);
    }

    @Override
    public void validateOrderSuccess(PurchaseResponse response) {
        hideProgressDialog();
        int code = response.getCode();

        if (code == 100) {
            PurchaseResponse.Result result = response.getResult();
            String orderNumber = result.getOrderNum();

            Intent intent = new Intent(PurchaseActivity.this, PurchaseCompleteActivity.class);
            intent.putExtra("ORDER_NUMBER", orderNumber);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else {
            String message = response.getMessage();
            showCustomToast(message);
        }
    }

    @Override
    public void validateOrderFailure(String message) {
        hideProgressDialog();
        showCustomToast(message != null ? message : String.valueOf(R.string.network_error));
    }
}
