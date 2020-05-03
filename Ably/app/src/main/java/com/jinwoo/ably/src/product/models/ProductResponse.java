package com.jinwoo.ably.src.product.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProductResponse {

    public class Result {
        @SerializedName("productIdx")
        private int productIdx;

        @SerializedName("productName")
        private String productName;

        @SerializedName("discountRatio")
        private String discountRatio;

        @SerializedName("displayedPrice")
        private String displayedPrice;

        @SerializedName("price")
        private String price;

        @SerializedName("productCode")
        private String productCode;

        @SerializedName("isMyHeart")
        private String isMyHeart;

        @SerializedName("contents")
        private String contents;

        @SerializedName("marketIdx")
        private int marketIdx;

        @SerializedName("marketName")
        private String marketName;

        @SerializedName("marketHashTags")
        private String marketHashTags;

        @SerializedName("marketThumbnailUrl")
        private String marketThumbnailUrl;

        @SerializedName("mainImgUrlList")
        private ArrayList<String> mainImgUrlList;

        @SerializedName("normalImgUrlList")
        private ArrayList<String> normalImgUrlList;

        public int getProductIdx() { return productIdx; }
        public String getProductName() { return productName; }
        public String getDiscountRatio() { return discountRatio; }
        public String getDisplayedPrice() { return displayedPrice; }
        public String getPrice() { return price; }
        public String getProductCode() { return productCode; }
        public String getIsMyHeart() { return isMyHeart; }
        public String getContents() { return contents; }
        public int getMarketIdx() { return marketIdx; }
        public String getMarketName() { return marketName; }
        public String getMarketHashTags() { return marketHashTags; }
        public String getMarketThumbnailUrl() { return marketThumbnailUrl; }
        public ArrayList<String> getMainImgUrlList() { return mainImgUrlList; }
        public ArrayList<String> getNormalImgUrlList() { return normalImgUrlList; }
    }

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private Result result;

    public boolean isSuccess() { return isSuccess; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public Result getResult() { return result; }
}
