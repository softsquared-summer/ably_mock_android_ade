package com.jinwoo.ably.src.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;

public class SignUpHomeActivity extends BaseActivity {

    private ImageView mExit;
    private Button mSignupWithEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_home);

        mExit = findViewById(R.id.signup1_iv_exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSignupWithEmail = findViewById(R.id.signup1_btn_signup_with_email);
        mSignupWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpHomeActivity.this, SignUpWithEmailActivity.class));
            }
        });
    }
}
