package com.jinwoo.ably.src.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.main.MainActivity;

public class SignUpComplete extends BaseActivity {

    private Button mProceed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_complete);

        mProceed = findViewById(R.id.sign_up_complete_btn_proceed);
        mProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpComplete.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
