package com.jinwoo.ably.src.product.fragments.options.data;

public class SelectedOption {

    private String option1, option2;
    private int idx, count, cost, stock;

    public SelectedOption(int idx, String option1, String option2, int cost, int stock) {
        this.idx        = idx;
        this.option1    = option1;
        this.option2    = option2;
        this.cost       = cost;
        this.stock      = stock;
        count = 1;
    }

    public int getIdx() { return idx; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    public int getCount() { return count; }
    public int getCost() { return cost; }
    public int getStock() { return stock; }
    public void setCount(int count) { this.count = count; }

}
