package com.jinwoo.ably.src.signin.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    public class Result {

        @SerializedName("jwt")
        private String jwt;

        @SerializedName("userIdx")
        private int userIdx;

        @SerializedName("userName")
        private String userName;

        public String getJwt() { return jwt; }
        public int getUserIdx() { return userIdx; }
        public String getUserName() { return userName; }
    }

    @SerializedName("result")
    private Result result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public Result getResult() { return result; }
    public boolean isSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}
