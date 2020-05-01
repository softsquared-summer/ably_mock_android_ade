package com.jinwoo.ably.src.signin.interfaces;

import com.jinwoo.ably.src.signin.models.SignInResponse;

public interface SignInActivityView {
    void validateSuccess(SignInResponse response);
    void validateFailure(String message);
}
