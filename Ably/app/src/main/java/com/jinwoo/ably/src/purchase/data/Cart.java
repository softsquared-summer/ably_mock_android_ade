package com.jinwoo.ably.src.purchase.data;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cartItems;
    private boolean open;

    public Cart(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
        open = false;
    }

    public ArrayList<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(ArrayList<CartItem> cartItems) { this.cartItems = cartItems; }
    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }
}
