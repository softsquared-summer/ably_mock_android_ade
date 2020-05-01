package com.jinwoo.ably.src.main.fragments.home.children.newproducts.data;

public class Product {

    private int productIdx;
    private String thumbnailUrl;
    private String discountRatio;
    private String displayedPrice;
    private int marketIdx;
    private String marketName;
    private String productName;
    private String purchaseCnt;
    private String isMyHeart;
    private String isHotDeal;
    private String isNew;

    public Product(int productIdx,
                   String thumbnailUrl,
                   String discountRatio,
                   String displayedPrice,
                   int marketIdx,
                   String marketName,
                   String productName,
                   String purchaseCnt,
                   String isMyHeart,
                   String isHotDeal,
                   String isNew) {

        this.productIdx = productIdx;
        this.thumbnailUrl = thumbnailUrl;
        this.discountRatio = discountRatio;
        this.displayedPrice = displayedPrice;
        this.marketIdx = marketIdx;
        this.marketName = marketName;
        this.productName = productName;
        this.purchaseCnt = purchaseCnt;
        this.isMyHeart = isMyHeart;
        this.isHotDeal = isHotDeal;
        this.isNew = isNew;

    }

    public int getProductIdx() { return productIdx; }
    public void setProductIdx(int productIdx) { this.productIdx = productIdx; }
    public String getThumbnailUrl() {  return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
    public String getDiscountRatio() { return discountRatio; }
    public void setDiscountRatio(String discountRatio) { this.discountRatio = discountRatio; }
    public String getDisplayedPrice() { return displayedPrice; }
    public void setDisplayedPrice(String displayedPrice) { this.displayedPrice = displayedPrice; }
    public int getMarketIdx() { return marketIdx; }
    public void setMarketIdx(int marketIdx) { this.marketIdx = marketIdx; }
    public String getMarketName() { return marketName; }
    public void setMarketName(String marketName) { this.marketName = marketName; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getPurchaseCnt() { return purchaseCnt; }
    public void setPurchaseCnt(String purchaseCnt) { this.purchaseCnt = purchaseCnt; }
    public String getIsMyHeart() { return isMyHeart; }
    public void setIsMyHeart(String isMyHeart) { this.isMyHeart = isMyHeart; }
    public String getIsHotDeal() { return isHotDeal; }
    public void setIsHotDeal(String isHotDeal) { this.isHotDeal = isHotDeal; }
    public String getIsNew() { return isNew; }
    public void setIsNew(String isNew) { this.isNew = isNew; }
}
