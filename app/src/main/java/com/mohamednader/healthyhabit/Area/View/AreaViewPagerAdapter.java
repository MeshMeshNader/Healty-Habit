package com.mohamednader.healthyhabit.Area.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.List;

public class AreaViewPagerAdapter extends FragmentPagerAdapter {

    private List<Meal> meals;

    public AreaViewPagerAdapter(FragmentManager fm, List<Meal> meals) {
        super(fm);
        this.meals = meals;
    }

    @Override
    public Fragment getItem(int i) {
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
        args.putString("EXTRA_DATA_NAME", meals.get(i).getStrArea());
        String flagURI = Utils.generateFlagUrl(meals.get(i).getStrArea());
        args.putString("EXTRA_DATA_IMAGE", flagURI);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return meals.get(position).getStrArea();
    }

}
