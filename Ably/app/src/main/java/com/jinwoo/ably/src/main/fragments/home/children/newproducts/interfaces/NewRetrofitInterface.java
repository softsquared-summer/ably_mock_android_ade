package com.jinwoo.ably.src.main.fragments.home.children.newproducts.interfaces;

import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.BestProductsResponse;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.models.NewProductsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewRetrofitInterface {

    @GET("/new-products/best")
    Call<BestProductsResponse> getBestProducts();

    @GET("/new-products")
    Call<NewProductsResponse> getNewProducts(@Query("page") int page);
}
