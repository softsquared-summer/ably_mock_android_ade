package com.jinwoo.ably.src.main.interfaces;

import com.jinwoo.ably.src.main.models.SignInResponse;

public interface MainActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void signInSuccess(SignInResponse.SignInResult signInResult);
}
