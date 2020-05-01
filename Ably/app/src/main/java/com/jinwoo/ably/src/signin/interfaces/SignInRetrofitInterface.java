package com.jinwoo.ably.src.signin.interfaces;

import com.jinwoo.ably.src.signin.models.SignInResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInRetrofitInterface {

    @POST("/signIn")
    Call<SignInResponse> getSignIn(@Body RequestBody requestBody);

}
