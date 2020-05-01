package com.jinwoo.ably.src.main.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.best.FragmentBest;
import com.jinwoo.ably.src.main.fragments.home.children.hotdeal.FragmentHotDeal;
import com.jinwoo.ably.src.main.fragments.home.children.newproducts.FragmentNew;
import com.jinwoo.ably.src.main.fragments.home.children.today.FragmentToday;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentToday();
            case 1:
                return new FragmentNew();
            case 2:
                return new FragmentBest();
            case 3:
                return new FragmentHotDeal();
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}
