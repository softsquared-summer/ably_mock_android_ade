package com.jinwoo.ably.src.purchase.activities.chooseaddress.fragments.addresslist.data;

public class Address {

    private String receiver, postalCode, address, detail, contact;
    private boolean selected;

    public Address(String receiver, String postalCode, String address, String detail, String contact) {
        this.receiver = receiver;
        this.postalCode = postalCode;
        this.address = address;
        this.detail = detail;
        this.contact = contact;
        selected = false;
    }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
