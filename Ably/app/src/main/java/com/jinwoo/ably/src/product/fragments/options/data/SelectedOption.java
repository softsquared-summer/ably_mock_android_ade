package com.jinwoo.ably.src.product.fragments.options.data;

public class SelectedOption {

    private String option1, option2;
    private int count, cost;

    public SelectedOption(String option1, String option2, int cost) {
        this.option1 = option1;
        this.option2 = option2;
        this.cost = cost;
        count = 1;
    }

    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    public int getCount() { return count; }
    public int getCost() { return cost; }
    public void setCount(int count) { this.count = count; }
}
