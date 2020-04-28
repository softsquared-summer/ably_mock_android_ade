package com.jinwoo.ably.src.signup.models;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("result")
    private String result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public String getResult() { return result; }
    public boolean getIsSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}