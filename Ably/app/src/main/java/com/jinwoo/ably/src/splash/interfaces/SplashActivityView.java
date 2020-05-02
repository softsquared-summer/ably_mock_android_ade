package com.jinwoo.ably.src.splash.interfaces;

import com.jinwoo.ably.src.splash.models.SplashResponse;

public interface SplashActivityView {
    public void validateSuccess(SplashResponse response);
    public void validateFailure(String message);
}
