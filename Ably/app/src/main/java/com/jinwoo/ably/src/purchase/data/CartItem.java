package com.jinwoo.ably.src.purchase.data;

public class CartItem {

    private int count, cost;
    private String productThumbnail, productName, marketName, option1, option2;

    public CartItem(int count, int cost, String productThumbnail, String productName, String marketName, String option1, String option2) {
        this.count = count;
        this.cost = cost;
        this.productThumbnail = productThumbnail;
        this.productName = productName;
        this.marketName = marketName;
        this.option1 = option1;
        this.option2 = option2;
    }

    public int getCount() { return count; }
    public int getCost() { return cost; }
    public String getProductThumbnail() { return productThumbnail; }
    public String getProductName() { return productName; }
    public String getMarketName() { return marketName; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
}
