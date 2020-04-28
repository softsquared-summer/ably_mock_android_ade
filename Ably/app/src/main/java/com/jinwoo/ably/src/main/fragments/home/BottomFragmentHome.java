package com.jinwoo.ably.src.main.fragments.home;

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
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentBest;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentHotDeal;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentNew;
import com.jinwoo.ably.src.main.fragments.home.children.TopFragmentToday;

public class BottomFragmentHome extends Fragment {

    private BottomNavigationView mTopNavigationView;

    public BottomFragmentHome() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_bottom_1, container, false);

        mTopNavigationView = view.findViewById(R.id.parent1_top_navigation);
        mTopNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.top_today:
                        TopFragmentToday topFragmentToday = new TopFragmentToday();
                        changeFragment(topFragmentToday);
                        break;
                    case R.id.top_new:
                        TopFragmentNew topFragmentNew = new TopFragmentNew();
                        changeFragment(topFragmentNew);
                        break;
                    case R.id.top_best:
                        TopFragmentBest topFragmentBest = new TopFragmentBest();
                        changeFragment(topFragmentBest);
                        break;
                    case R.id.top_hot_deal:
                        TopFragmentHotDeal topFragmentHotDeal = new TopFragmentHotDeal();
                        changeFragment(topFragmentHotDeal);
                        break;
                }
                return true;
            }
        });

        TopFragmentToday frag = new TopFragmentToday();
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
