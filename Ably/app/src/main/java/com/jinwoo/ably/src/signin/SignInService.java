package com.jinwoo.ably.src.signin;

import android.util.Log;

import com.jinwoo.ably.src.signin.interfaces.SignInActivityView;
import com.jinwoo.ably.src.signin.interfaces.SignInRetrofitInterface;
import com.jinwoo.ably.src.signin.models.SignInResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class SignInService {

    private final SignInActivityView signInActivityView;

    SignInService(SignInActivityView signInActivityView) {
        this.signInActivityView = signInActivityView;
    }

    void getSignIn(RequestBody requestBody) {

        final SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);

        signInRetrofitInterface.getSignIn(requestBody).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body();

                if (signInResponse == null) {
                    signInActivityView.validateFailure(null);
                    return;
                }

                signInActivityView.validateSuccess(signInResponse);
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                signInActivityView.validateFailure(null);
                Log.d("ON FAILURE", t.toString());
            }
        });
    }

}
