package com.jinwoo.ably.src.main.interfaces;

import com.jinwoo.ably.src.main.models.DefaultResponse;
import com.jinwoo.ably.src.main.models.SignInBody;
import com.jinwoo.ably.src.main.models.SignInResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<SignInResponse> postSignIn(@Body SignInBody params);
}
