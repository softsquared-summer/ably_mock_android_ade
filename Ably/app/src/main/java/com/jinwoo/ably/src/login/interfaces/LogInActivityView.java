package com.jinwoo.ably.src.login.interfaces;

import com.jinwoo.ably.src.login.models.LogInResponse;

public interface LogInActivityView {
    void validateSuccess(LogInResponse response);
    void validateFailure(String message);
}
