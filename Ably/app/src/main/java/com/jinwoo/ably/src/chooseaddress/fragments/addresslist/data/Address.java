package com.jinwoo.ably.src.chooseaddress.fragments.addresslist.data;

public class Address {

    private String receiver, postNumber, address, detail, contact;
    private boolean selected;

    public Address(String receiver, String postNumber, String address, String detail, String contact) {
        this.receiver = receiver;
        this.postNumber = postNumber;
        this.address = address;
        this.detail = detail;
        this.contact = contact;
        selected = false;
    }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    public String getPostNumber() { return postNumber; }
    public void setPostNumber(String postNumber) { this.postNumber = postNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
