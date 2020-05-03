package com.jinwoo.ably.src.product.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.jinwoo.ably.src.product.fragments.info.InfoFragment;
import com.jinwoo.ably.src.product.fragments.related.RelatedFragment;
import com.jinwoo.ably.src.product.fragments.review.ReviewFragment;
import com.jinwoo.ably.src.product.fragments.support.SupportFragment;

import java.util.ArrayList;

public class PagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    private int numOfTabs;
    private ArrayList<String> infoImages;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs, ArrayList<String> infoImages) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
        this.infoImages = infoImages;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoFragment(infoImages);
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
