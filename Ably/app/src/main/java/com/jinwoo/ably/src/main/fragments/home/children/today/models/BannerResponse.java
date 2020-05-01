package com.jinwoo.ably.src.main.fragments.home.children.today.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerResponse {

    public class Result {

        @SerializedName("bannerIdx")
        private int bannerIdx;

        @SerializedName("bannerName")
        private String bannerName;

        @SerializedName("bannerUrl")
        private String bannerUrl;

        public int getBannerIdx() { return bannerIdx; }
        public String getBannerName() { return bannerName; }
        public String getBannerUrl() { return bannerUrl; }
    }

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private ArrayList<Result> result;

    public boolean isSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public ArrayList<Result> getResult() { return result; }
}
