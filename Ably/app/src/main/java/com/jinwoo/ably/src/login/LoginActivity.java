package com.jinwoo.ably.src.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;

public class LoginActivity extends BaseActivity {

    private ImageView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBack = (ImageView) findViewById(R.id.login_iv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
