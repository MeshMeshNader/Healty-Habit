package com.mohamednader.healthyhabit.Category.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryHomeFragment extends Fragment {


    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    CategoryViewPagerAdapter adapter;

    public CategoryHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category_home, container, false);

        initViews();
        initIntent();


        return view;
    }

    private void initViews() {

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
    }

    private void initIntent() {
        List<Category> categories = new ArrayList<>();
        if (getArguments() != null) {
            categories = (List<Category>) getArguments().getSerializable("category");
        }

        adapter = new CategoryViewPagerAdapter(
                getChildFragmentManager(),
                categories);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0, true);
        adapter.notifyDataSetChanged();

    }

}