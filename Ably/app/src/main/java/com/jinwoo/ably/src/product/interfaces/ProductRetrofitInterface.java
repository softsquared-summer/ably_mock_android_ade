package com.jinwoo.ably.src.product.interfaces;

import com.jinwoo.ably.src.product.models.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductRetrofitInterface {

    @GET("/products/{productIdx}")
    Call<ProductResponse> getProduct(@Path("productIdx") int productIdx);

}
