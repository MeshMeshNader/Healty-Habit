package com.mohamednader.healthyhabit.Home.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mohamednader.healthyhabit.Home.Presenter.HomePresenter;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeViewInterface{

    private final String TAG =  "HomeActivity_TAG";
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.i(TAG, "onCreate: ");

        homePresenter = new HomePresenter(this,
                Repository.getInstance(this, ApiClient.getInstance()));

        homePresenter.getMealsByLetterFilter('g');
    }

    @Override
    public void showMealsByLetterFilter(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showMealsByLetterFilter: " + list.get(i).getStrMeal());
    }
}