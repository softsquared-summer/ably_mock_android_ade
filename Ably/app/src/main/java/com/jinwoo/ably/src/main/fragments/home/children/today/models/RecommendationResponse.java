package com.jinwoo.ably.src.main.fragments.home.children.today.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecommendationResponse {

    public class Result {
        @SerializedName("productIdx")
        private int productIdx;

        @SerializedName("thumnailUrl")
        private String thumnailUrl;

        @SerializedName("discountRatio")
        private String discountRatio;

        @SerializedName("displayedPrice")
        private String displayedPrice;

        @SerializedName("marketIdx")
        private int marketIdx;

        @SerializedName("marketName")
        private String marketName;

        @SerializedName("productName")
        private String productName;

        @SerializedName("purchaseCnt")
        private String purchaseCnt;

        @SerializedName("isMyHeart")
        private String isMyHeart;

        @SerializedName("isHotDeal")
        private String isHotDeal;

        @SerializedName("isNew")
        private String isNew;

        public int getProductIdx() { return productIdx; }
        public String getThumnailUrl() { return thumnailUrl; }
        public String getDiscountRatio() { return discountRatio; }
        public String getDisplayedPrice() { return displayedPrice; }
        public int getMarketIdx() { return marketIdx; }
        public String getMarketName() { return marketName; }
        public String getProductName() { return productName; }
        public String getPurchaseCnt() { return purchaseCnt; }
        public String getIsMyHeart() { return isMyHeart; }
        public String getIsHotDeal() { return isHotDeal; }
        public String getIsNew() { return isNew; }
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
