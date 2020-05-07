package com.jinwoo.ably.src.purchase.models;

import com.google.gson.annotations.SerializedName;

public class PurchaseResponse {

    public class Result {
        @SerializedName("orderNum")
        private String orderNum;

        public String getOrderNum() {
            return orderNum;
        }
    }

    @SerializedName("result")
    private Result result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public Result getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
