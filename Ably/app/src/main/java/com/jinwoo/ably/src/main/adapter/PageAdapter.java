package com.jinwoo.ably.src.main.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentBest;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentHotDeal;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentNew;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentToday;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TopFragmentToday();
            case 1:
                return new TopFragmentNew();
            case 2:
                return new TopFragmentBest();
            case 3:
                return new TopFragmentHotDeal();
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}
