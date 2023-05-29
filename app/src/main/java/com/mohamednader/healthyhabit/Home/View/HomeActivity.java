package com.mohamednader.healthyhabit.Home.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mohamednader.healthyhabit.Home.Presenter.HomePresenter;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
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
        homePresenter.getRandomMeal();
        homePresenter.getMealDetailsByID(52772);
        homePresenter.getListCategoriesNames();
        homePresenter.getListAreasNames();
        homePresenter.getListIngredientsNames();
        homePresenter.getMealsByCategory("Beef");
        homePresenter.getMealsByArea("Egyptian");
        homePresenter.getMealsByIngredient("Chicken");
        homePresenter.getListCategoriesDetails();
    }

    @Override
    public void showMealsByLetterFilter(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showMealsByLetterFilter: " + list.get(i).getStrMeal());
    }

    @Override
    public void showRandomMeal(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showRandomMeal: " + list.get(i).getStrMeal());
    }

    @Override
    public void showMealDetailsByID(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showMealDetailsByID: " + list.get(i).getStrMeal());
    }

    @Override
    public void showListCategoriesNames(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showListCategoriesNames: " + list.get(i).getStrCategory());
    }

    @Override
    public void showListAreasNames(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showListAreasNames: " + list.get(i).getStrArea());
    }

    @Override
    public void showListIngredientsNames(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showListIngredientsNames: " + list.get(i).getStrIngredientItem());
    }

    @Override
    public void showMealsByCategory(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showMealsByCategory: " + list.get(i).getStrMeal());
    }

    @Override
    public void showMealsByArea(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showMealsByArea: " + list.get(i).getStrMeal());
    }

    @Override
    public void showMealsByIngredient(List<Meal> list) {
        for(int i = 0; i < list.size() ; i++)
            Log.i(TAG, "showMealsByIngredient: " + list.get(i).getStrMeal());
    }

    @Override
    public void showListCategoriesDetails(List<Category> list) {

    }
}