package com.jinwoo.ably.src.login.interfaces;

import com.jinwoo.ably.src.login.models.LogInResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogInRetrofitInterface {

    @POST("/login")
    Call<LogInResponse> getLogIn(@Body RequestBody requestBody);

}
