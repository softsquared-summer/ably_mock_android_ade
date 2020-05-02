package com.jinwoo.ably.src.splash.interfaces;

import com.jinwoo.ably.src.splash.models.SplashResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashRetrofitInterface {
    @GET("/signIn")
    Call<SplashResponse> getSignIn();
}
