package com.jinwoo.ably.src.main.data;

import java.util.ArrayList;

public class Product {

    private int gif;
    private ArrayList<Integer> photos;
    private int discount;
    private int price;
    private String market;
    private String name;
    private String tag;
    private String sales;
    private boolean isPicked;

    public Product(int gif, ArrayList<Integer> photos, int discout, int price, String market, String name, String tag, String sales) {
        this.gif = gif;
        this.photos = photos;
        this.discount = discout;
        this.price = price;
        this.market = market;
        this.name = name;
        this.tag = tag;
        this.sales = sales;
        isPicked = false;
    }

    public int getGif() { return gif; }

    public void setGif(int gif) { this.gif = gif; }

    public ArrayList<Integer> getPhotos() { return photos; }

    public ArrayList<Integer> getPhoto() {
        return photos;
    }

    public void setPhotos(ArrayList<Integer> photos) { this.photos = photos; }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public boolean isPicked() { return isPicked; }

    public void setPicked(boolean isPicked) { this.isPicked = isPicked; }
}
