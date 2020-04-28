package com.jinwoo.ably.src.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.signup.interfaces.SignUpActivityView;
import com.jinwoo.ably.src.signup.models.SignUpBody;
import com.jinwoo.ably.src.signup.models.SignUpResponse;

public class SignUpActivity2 extends BaseActivity implements SignUpActivityView {

    private ImageView mBack;
    private EditText mEmail, mPassword, mPasswordConfirm, mName, mPhone;
    private Button mAuthenticate, mSignup;
    private String userEmail, userPassword, userPasswordConfirm, userDateOfBirth, userName, userPhone;
    private SignUpService mSignUpService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_2);
        mSignUpService = new SignUpService(SignUpActivity2.this);
        mapWidgets();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Validate user input & sign up
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = mEmail.getText().toString();
                userPassword = mPassword.getText().toString();
                userPasswordConfirm = mPasswordConfirm.getText().toString();
                userName = mName.getText().toString();
                userPhone = mPhone.getText().toString();

                //TODO: VALIDATION PROCESS

                SignUpBody signUpBody = new SignUpBody("NORMAL",
                                                        userEmail,
                                                        userPassword,
                                                        userName,
                                                        userPhone,
                                                        "1993.02.17",
                                                        "Y",
                                                        "Y");
                tryPostSignUp(signUpBody);
            }
        });

        mAuthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void mapWidgets(){
        mBack = findViewById(R.id.signup2_iv_back);
        mEmail = findViewById(R.id.signup2_et_email);
        mPassword = findViewById(R.id.signup2_et_password);
        mPasswordConfirm = findViewById(R.id.signup2_et_password_confirm);
        mName = findViewById(R.id.signup2_et_name);
        mPhone = findViewById(R.id.signup2_et_phone);
        mAuthenticate = findViewById(R.id.signup2_btn_auth);
        mSignup = findViewById(R.id.signup2_btn_signup);
    }

    private void tryPostSignUp(SignUpBody signUpBody) {
        showProgressDialog();
        mSignUpService.postSignUp(signUpBody);
    }

    @Override
    public void signUpSuccess(SignUpResponse response) {
        hideProgressDialog();
        showCustomToast("SIGNED UP");
        // What to do when sign in succeeds
    }

    @Override
    public void signUpFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}