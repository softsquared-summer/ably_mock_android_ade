package com.jinwoo.ably.src.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;

public class SignupActivity1 extends BaseActivity {

    private ImageView mExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_1);

        mExit = (ImageView) findViewById(R.id.signup1_iv_exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
