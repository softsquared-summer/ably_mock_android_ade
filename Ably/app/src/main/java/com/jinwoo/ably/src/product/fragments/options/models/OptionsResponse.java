package com.jinwoo.ably.src.product.fragments.options.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OptionsResponse {

    public class Result {
        @SerializedName("detailedProductIdx")
        private int detailedProductIdx;

        @SerializedName("firstOption")
        private String firstOption;

        @SerializedName("secondOption")
        private String secondOption;

        @SerializedName("detailedPrice")
        private int detailedPrice;

        @SerializedName("stock")
        private int stock;

        public int getDetailedProductIdx() { return detailedProductIdx; }
        public String getFirstOption() { return firstOption; }
        public String getSecondOption() { return secondOption; }
        public int getDetailedPrice() { return detailedPrice; }
        public int getStock() { return stock; }
    }

    @SerializedName("result")
    private ArrayList<Result> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public ArrayList<Result> getResult() { return result; }
    public boolean isSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}
