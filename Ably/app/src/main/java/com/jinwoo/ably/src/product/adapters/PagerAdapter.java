package com.jinwoo.ably.src.product.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.jinwoo.ably.src.product.fragments.InfoFragment;
import com.jinwoo.ably.src.product.fragments.RelatedFragment;
import com.jinwoo.ably.src.product.fragments.ReviewFragment;
import com.jinwoo.ably.src.product.fragments.SupportFragment;

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
                return new InfoFragment();
            case 1:
                return new ReviewFragment();
            case 2:
                return new RelatedFragment();
            case 3:
                return new SupportFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}