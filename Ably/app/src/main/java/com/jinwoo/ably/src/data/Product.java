package com.jinwoo.ably.src.data;

public class Product {

    private int photo;
    private int price;
    private String name;
    private String description;
    private String sales;

    public Product(int photo, int price, String name, String description, String sales) {
        this.photo = photo;
        this.price = price;
        this.name = name;
        this.description = description;
        this.sales = sales;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
}
