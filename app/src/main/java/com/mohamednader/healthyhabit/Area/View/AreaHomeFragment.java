package com.mohamednader.healthyhabit.Area.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.R;

import java.util.ArrayList;
import java.util.List;


public class AreaHomeFragment extends Fragment {

    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    AreaViewPagerAdapter adapter;

    public AreaHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_area_home, container, false);

        initViews();
        initIntent();

        return view;
    }


    private void initViews() {

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
    }

    private void initIntent() {
        List<Meal> meals = new ArrayList<>();
        if (getArguments() != null) {
            meals = (List<Meal>) getArguments().getSerializable("meal");
        }

        adapter = new AreaViewPagerAdapter(
                getChildFragmentManager(),
                meals);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0, true);
        adapter.notifyDataSetChanged();

    }

}