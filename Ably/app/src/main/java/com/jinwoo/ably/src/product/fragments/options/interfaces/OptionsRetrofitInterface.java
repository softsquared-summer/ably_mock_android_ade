package com.jinwoo.ably.src.product.fragments.options.interfaces;

import com.jinwoo.ably.src.product.fragments.options.models.OptionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OptionsRetrofitInterface {
    @GET("/products/{productIdx}/options")
    public Call<OptionsResponse> getOptions(@Path("productIdx") int productIdx);
}
