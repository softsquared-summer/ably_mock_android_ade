package com.jinwoo.ably.src.signup.interfaces;

import com.jinwoo.ably.src.signup.models.SignUpResponse;

public interface SignUpActivityView {

    void signUpSuccess(SignUpResponse response);

    void signUpFailure(String message);

}
