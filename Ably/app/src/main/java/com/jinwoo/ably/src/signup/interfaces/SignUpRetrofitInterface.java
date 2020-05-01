package com.jinwoo.ably.src.signup.interfaces;

import com.jinwoo.ably.src.signup.models.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    @POST("/signUp")
    Call<SignUpResponse> postSignUp(@Body RequestBody params);
}
