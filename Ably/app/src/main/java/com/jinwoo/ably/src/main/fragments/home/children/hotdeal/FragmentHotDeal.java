package com.jinwoo.ably.src.main.fragments.home.children.hotdeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jinwoo.ably.R;

public class FragmentHotDeal extends Fragment {

    public FragmentHotDeal() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_home_hotdeal, container, false);
    }
}
