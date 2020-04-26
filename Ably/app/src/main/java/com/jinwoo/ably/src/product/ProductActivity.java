package com.jinwoo.ably.src.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;

public class ProductActivity extends BaseActivity {

    private ImageView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        //TODO: get product url from the intent and parse, map it onto the UI of this activity
        Intent intent = getIntent();

        mBack = (ImageView) findViewById(R.id.product_iv_back);
        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
