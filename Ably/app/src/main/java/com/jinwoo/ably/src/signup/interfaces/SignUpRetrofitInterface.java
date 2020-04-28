package com.jinwoo.ably.src.signup.interfaces;

import com.jinwoo.ably.src.signup.models.SignUpBody;
import com.jinwoo.ably.src.signup.models.SignUpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    @POST("/register")
    Call<SignUpResponse> postSignUp(@Body SignUpBody params);
}
