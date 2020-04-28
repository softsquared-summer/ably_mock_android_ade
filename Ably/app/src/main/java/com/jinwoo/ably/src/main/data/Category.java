package com.jinwoo.ably.src.main.data;

import java.util.ArrayList;

public class Category {
    public ArrayList<String> child;
    public String groupName;
    public boolean open;

    public Category(String groupName) {
        this.groupName = groupName;
        child = new ArrayList<String>();
        open = false;
    }
}
