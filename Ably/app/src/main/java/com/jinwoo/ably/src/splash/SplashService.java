package com.jinwoo.ably.src.splash;

import android.util.Log;
import com.jinwoo.ably.src.splash.interfaces.SplashActivityView;
import com.jinwoo.ably.src.splash.interfaces.SplashRetrofitInterface;
import com.jinwoo.ably.src.splash.models.SplashResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class SplashService {

    final SplashActivityView splashActivityView;

    public SplashService(SplashActivityView splashActivityView) {
        this.splashActivityView = splashActivityView;
    }

    public void getSignIn() {
        SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);

        splashRetrofitInterface.getSignIn().enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {
                SplashResponse splashResponse = response.body();
                if (splashResponse == null) {
                    splashActivityView.validateFailure(null);
                    return;
                }

                splashActivityView.validateSuccess(splashResponse);
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                splashActivityView.validateFailure(null);
                Log.d("THROWABLE", t.toString());
            }
        });
    }
}
