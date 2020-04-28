package com.jinwoo.ably.src.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;

public class BottomFragment1 extends Fragment {

    private BottomNavigationView mTopNavigationView;

    public BottomFragment1() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_bottom_1, container, false);

        mTopNavigationView = (BottomNavigationView) view.findViewById(R.id.parent1_top_navigation);
        mTopNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.top_today:
                        TopFragment1 topFragment1 = new TopFragment1();
                        changeFragment(topFragment1);
                        break;
                    case R.id.top_new:
                        TopFragment2 topFragment2 = new TopFragment2();
                        changeFragment(topFragment2);
                        break;
                    case R.id.top_best:
                        TopFragment3 topFragment3 = new TopFragment3();
                        changeFragment(topFragment3);
                        break;
                    case R.id.top_hot_deal:
                        TopFragment4 topFragment4 = new TopFragment4();
                        changeFragment(topFragment4);
                        break;
                }
                return true;
            }
        });

        TopFragment1 frag = new TopFragment1();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.parent1_child_container, frag);
        transaction.commit();

        return view;
    }

    private void changeFragment(Fragment frag){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.parent1_child_container, frag);
        transaction.commit();
    }
}
