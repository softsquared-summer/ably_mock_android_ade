package com.jinwoo.ably.src.purchase.activities.complete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.MainActivity;

public class PurchaseCompleteActivity extends BaseActivity {

    private TextView mOrderNumber;
    private Button mContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_complete);
        mapWidgets();

        Intent intent = getIntent();
        String orderNumber = "주문번호: " + intent.getStringExtra("ORDER_NUMBER");
        mOrderNumber.setText(orderNumber);

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PurchaseCompleteActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void mapWidgets() {
        mOrderNumber    = findViewById(R.id.purchase_complete_tv_order_number);
        mContinue       = findViewById(R.id.purchase_complete_btn_continue);
    }
}
