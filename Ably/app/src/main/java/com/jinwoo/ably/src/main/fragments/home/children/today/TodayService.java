package com.jinwoo.ably.src.main.fragments.home.children.today;

import com.jinwoo.ably.src.main.fragments.home.children.today.interfaces.TodayRetrofitInterface;
import com.jinwoo.ably.src.main.fragments.home.children.today.interfaces.TodayFragmentView;
import com.jinwoo.ably.src.main.fragments.home.children.today.models.BannerResponse;
import com.jinwoo.ably.src.main.fragments.home.children.today.models.RecommendationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jinwoo.ably.src.ApplicationClass.getRetrofit;

public class TodayService {

    final TodayFragmentView todayFragmentView;

    public TodayService(TodayFragmentView todayFragmentView) {
        this.todayFragmentView = todayFragmentView;
    }

    public void getBanners() {
        final TodayRetrofitInterface todayRetrofitInterface = getRetrofit().create(TodayRetrofitInterface.class);

        todayRetrofitInterface.getBanners().enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {

                final BannerResponse bannerResponse = response.body();

                if (response == null) {
                    todayFragmentView.validateFailure(null);
                    return;
                }

                todayFragmentView.validateBannerSuccess(bannerResponse);
            }
            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                todayFragmentView.validateFailure(null);
            }
        });

    }

    public void getRecommendations(int page) {
        final TodayRetrofitInterface todayRetrofitInterface = getRetrofit().create(TodayRetrofitInterface.class);

        todayRetrofitInterface.getRecommendations(page).enqueue(new Callback<RecommendationResponse>() {
            @Override
            public void onResponse(Call<RecommendationResponse> call, Response<RecommendationResponse> response) {

                final RecommendationResponse recommendationResponse = response.body();

                if(response == null) {
                    todayFragmentView.validateFailure(null);
                    return;
                }

                todayFragmentView.validateRecommendationSuccess(recommendationResponse);
            }

            @Override
            public void onFailure(Call<RecommendationResponse> call, Throwable t) {
                todayFragmentView.validateFailure(null);
            }
        });
    }
}
