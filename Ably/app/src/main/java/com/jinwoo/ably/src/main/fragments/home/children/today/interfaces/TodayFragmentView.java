package com.jinwoo.ably.src.main.fragments.home.children.today.interfaces;

import com.jinwoo.ably.src.main.fragments.home.children.today.models.BannerResponse;
import com.jinwoo.ably.src.main.fragments.home.children.today.models.RecommendationResponse;

public interface TodayFragmentView {
    void validateBannerSuccess(BannerResponse bannerResponse);
    void validateRecommendationSuccess(RecommendationResponse recommendationResponse);
    void validateFailure(String message);
}
