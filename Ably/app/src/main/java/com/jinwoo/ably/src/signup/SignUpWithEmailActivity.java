package com.jinwoo.ably.src.signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.signup.interfaces.SignUpActivityView;
import com.jinwoo.ably.src.signup.models.SignUpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.RequestBody;
import static com.jinwoo.ably.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.jinwoo.ably.src.ApplicationClass.USER_NAME;
import static com.jinwoo.ably.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.jinwoo.ably.src.ApplicationClass.sSharedPreferences;

public class SignUpWithEmailActivity extends BaseActivity implements SignUpActivityView {

    private ImageView mBack;
    private EditText mEmail, mPassword, mPasswordConfirm, mName, mPhone;
    private View mDatePicker;
    private TextView mDateOfBirth;
    private Button mAuthenticate, mSignUp;
    private LinearLayout mAgreeAll;
    private CheckBox mAgreeAllCheckbox, mAgreeOnService, mAgreeOnPrivacy;
    private String userEmail, userPassword, userPasswordConfirm, userDateOfBirth, userName, userPhone;
    private SignUpService mSignUpService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_with_email);
        mSignUpService = new SignUpService(SignUpWithEmailActivity.this);
        mapWidgets();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(SignUpWithEmailActivity.this, DatePickerActivity.class), 1);
            }
        });

        // Validate user input & sign up
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = mEmail.getText().toString();
                userPassword = mPassword.getText().toString();
                userPasswordConfirm = mPasswordConfirm.getText().toString();
                userName = mName.getText().toString();
                userPhone = mPhone.getText().toString();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userType", "NORMAL");
                    jsonObject.put("email", userEmail);
                    jsonObject.put("password", userPassword);
                    jsonObject.put("name", userName);
                    jsonObject.put("phone", userPhone);
                    jsonObject.put("dateOfBirth", userDateOfBirth);
                    jsonObject.put("AgreeOnService", mAgreeOnService.isChecked() ? "Y" : "N");
                    jsonObject.put("AgreeOnPrivate", mAgreeOnPrivacy.isChecked() ? "Y" : "N");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonObject.toString());
                tryPostSignUp(requestBody);
            }
        });

        // Authenticate by phone
        mAuthenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Press 'check all'
        mAgreeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAgreeAllCheckbox.isChecked()) {
                    mAgreeAllCheckbox.setChecked(false);
                    mAgreeOnService.setChecked(false);
                    mAgreeOnPrivacy.setChecked(false);
                }
                else {
                    mAgreeAllCheckbox.setChecked(true);
                    mAgreeOnService.setChecked(true);
                    mAgreeOnPrivacy.setChecked(true);
                }
            }
        });
    }

    private void mapWidgets(){
        mBack               = findViewById(R.id.sign_up_with_email_iv_back);
        mEmail              = findViewById(R.id.sign_up_with_email_et_email);
        mPassword           = findViewById(R.id.sign_up_with_email_et_password);
        mPasswordConfirm    = findViewById(R.id.sign_up_with_email_et_password_confirm);
        mName               = findViewById(R.id.sign_up_with_email_et_name);
        mPhone              = findViewById(R.id.sign_up_with_email_et_phone);
        mAuthenticate       = findViewById(R.id.sign_up_with_email_btn_auth);
        mSignUp             = findViewById(R.id.sign_up_with_email_btn_signup);
        mDatePicker         = findViewById(R.id.sign_up_with_email_date_picker);
        mDateOfBirth        = findViewById(R.id.sign_up_with_email_date_of_birth);
        mAgreeAll           = findViewById(R.id.sign_up_with_email_agree_all);
        mAgreeAllCheckbox   = findViewById(R.id.sign_up_with_email_cb_agree_all);
        mAgreeOnService     = findViewById(R.id.sign_up_with_email_cb_service);
        mAgreeOnPrivacy     = findViewById(R.id.sign_up_with_email_cb_privacy);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode) {
            case 1:
                String yy = data.getStringExtra("YEAR");
                String mm = data.getStringExtra("MONTH");
                String dd = data.getStringExtra("DAY");
                String displayDate = yy + "년 " + mm + "월 " + dd + "일";
                userDateOfBirth = yy + mm + dd;
                mDateOfBirth.setText(displayDate);
                mDateOfBirth.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case 2:
                userDateOfBirth = "";
                mDateOfBirth.setText("생년월일 입력");
                mDateOfBirth.setTextColor(getResources().getColor(R.color.colorLightGrey));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + resultCode);
        }
    }

    private void tryPostSignUp(RequestBody requestBody) {
        showProgressDialog();
        mSignUpService.postSignUp(requestBody);
    }

    @Override
    public void signUpSuccess(SignUpResponse response) {
        hideProgressDialog();

        int code = response.getCode();
        String message = response.getMessage();

        if (code == 100) {
            String jwt = response.getResult();
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, jwt);
            editor.putString(USER_NAME, userName);
            editor.commit();

            Intent intent = new Intent(SignUpWithEmailActivity.this, SignUpComplete.class);
            startActivity(intent);
            finish();
        }
        else {
            showCustomToast(message);
        }

    }

    @Override
    public void signUpFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}