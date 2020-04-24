package com.jinwoo.ably.src.main.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    public class SignInResult {
        @SerializedName("jwt")
        private String jwt;

        @SerializedName("userNo")
        private String userNo;

        public String getJwt() { return jwt; }
        public String getUserNo() { return userNo; }
    }

    @SerializedName("result")
    private SignInResult signInResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public SignInResult getSignInResult() { return signInResult; }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public boolean getIsSuccess() { return isSuccess; }
}