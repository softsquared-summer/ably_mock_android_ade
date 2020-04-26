package com.jinwoo.ably.src.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.MainActivity;

public class ParentFragment1 extends Fragment {

    //private Toolbar mToolbar;
    private BottomNavigationView mTopNavigationView;

    public ParentFragment1() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_parent_1, container, false);

        //mToolbar = (Toolbar) view.findViewById(R.id.parent1_toolbar);
        //((MainActivity)getActivity()).setSupportActionBar(mToolbar);

        mTopNavigationView = (BottomNavigationView) view.findViewById(R.id.parent1_top_navigation);
        mTopNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.top_today:
                        ChildFragment1 childFragment1 = new ChildFragment1();
                        changeFragment(childFragment1);
                        break;
                    case R.id.top_new:
                        ChildFragment2 childFragment2 = new ChildFragment2();
                        changeFragment(childFragment2);
                        break;
                    case R.id.top_best:
                        ChildFragment3 childFragment3 = new ChildFragment3();
                        changeFragment(childFragment3);
                        break;
                    case R.id.top_hot_deal:
                        ChildFragment4 childFragment4 = new ChildFragment4();
                        changeFragment(childFragment4);
                        break;
                }
                return true;
            }
        });

        ChildFragment1 frag = new ChildFragment1();
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
