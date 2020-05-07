package com.jinwoo.ably.src.purchase.interfaces;

import com.jinwoo.ably.src.purchase.models.PurchaseResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PurchaseRetrofitInterface {

    @POST("/orders")
    Call<PurchaseResponse> postOrder(@Body RequestBody params);
}
