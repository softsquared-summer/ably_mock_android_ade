package com.jinwoo.ably.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LogInResponse {

    @SerializedName("result")
    private String result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public String getResult() { return result; }
    public boolean isSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}
