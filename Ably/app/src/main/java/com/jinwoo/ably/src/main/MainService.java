package com.jinwoo.ably.src.main;

import com.jinwoo.ably.src.main.interfaces.MainActivityView;
import com.jinwoo.ably.src.main.interfaces.MainRetrofitInterface;
import com.jinwoo.ably.src.main.models.DefaultResponse;
import com.jinwoo.ably.src.main.models.SignInBody;
import com.jinwoo.ably.src.main.models.SignInResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);

        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void postSignIn(String id, String pw) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);

        mainRetrofitInterface.postSignIn(new SignInBody(id, pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                final SignInResponse signInResponse = response.body();
                if (signInResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.signInSuccess(signInResponse.getSignInResult());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
