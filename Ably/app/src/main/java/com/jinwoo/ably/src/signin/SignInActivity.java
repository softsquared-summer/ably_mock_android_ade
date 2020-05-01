package com.jinwoo.ably.src.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;
import com.jinwoo.ably.src.signin.interfaces.SignInActivityView;
import com.jinwoo.ably.src.signin.models.SignInResponse;
import com.jinwoo.ably.src.main.MainActivity;
import com.jinwoo.ably.src.signup.SignUpWithEmailActivity;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.RequestBody;
import static com.jinwoo.ably.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.jinwoo.ably.src.ApplicationClass.sSharedPreferences;

public class SignInActivity extends BaseActivity implements SignInActivityView {

    private ImageView mBack;
    private EditText mEmail, mPassword;
    private Button mLogIn, mSignUp;
    private SignInService mSignInService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapWidgets();
        mSignInService = new SignInService(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = mEmail.getText().toString();
                String userPassword = mPassword.getText().toString();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userType", "NORMAL");
                    jsonObject.put("email", userEmail);
                    jsonObject.put("password", userPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonObject.toString());

                trySignIn(requestBody);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpWithEmailActivity.class));
                finish();
            }
        });
    }

    private void mapWidgets() {
        mBack =         findViewById(R.id.login_iv_back);
        mEmail =        findViewById(R.id.login_et_email);
        mPassword =     findViewById(R.id.login_et_password);
        mLogIn =        findViewById(R.id.login_btn_login);
        mSignUp =       findViewById(R.id.login_btn_signin);
    }

    private void trySignIn(RequestBody requestBody) {
        showProgressDialog();
        mSignInService.getSignIn(requestBody);
    }

    @Override
    public void validateSuccess(SignInResponse response) {
        hideProgressDialog();
        int code = response.getCode();
        String message = response.getMessage();

        if (code == 100) {
            showCustomToast(message);

            SignInResponse.Result result = response.getResult();
            String userName = result.getUserName();
            String jwt = result.getJwt();
            sSharedPreferences.edit().putString("jwt", jwt);

            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("LOG_IN", true);
            intent.putExtra("NAME", userName);
            startActivity(intent);
            finish();
        }
        else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
