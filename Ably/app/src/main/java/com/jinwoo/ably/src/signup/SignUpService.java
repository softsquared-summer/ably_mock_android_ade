package com.jinwoo.ably.src.signup;

import android.util.Log;
import com.jinwoo.ably.src.signup.interfaces.SignUpActivityView;
import com.jinwoo.ably.src.signup.interfaces.SignUpRetrofitInterface;
import com.jinwoo.ably.src.signup.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpService(final SignUpActivityView signupActivityView) {
        this.mSignUpActivityView = signupActivityView;
    }

    void postSignUp(RequestBody requestBody) {
        final SignUpRetrofitInterface signupRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);

        signupRetrofitInterface.postSignUp(requestBody).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signupResponse = response.body();
                if (signupResponse == null) {
                    mSignUpActivityView.signUpFailure(null);
                    return;
                }

                mSignUpActivityView.signUpSuccess(signupResponse);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.signUpFailure(null);
                Log.d("THROWABLE", t.toString());
            }
        });
    }
}