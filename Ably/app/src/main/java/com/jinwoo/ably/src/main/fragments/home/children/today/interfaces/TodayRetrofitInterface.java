package com.jinwoo.ably.src.main.fragments.home.children.today.interfaces;

import com.jinwoo.ably.src.main.fragments.home.children.today.models.BannerResponse;
import com.jinwoo.ably.src.main.fragments.home.children.today.models.RecommendationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TodayRetrofitInterface {

    @GET("/banner")
    Call<BannerResponse> getBanners();

    @GET("/recommended-products")
    Call<RecommendationResponse> getRecommendations(@Query("page") int page);
}
