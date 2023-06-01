package com.mohamednader.healthyhabit.Database;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface LocalSource {

    public LiveData<List<Meal>> getStoredMeals();

    public void insertMeal(Meal meal);

    public void deleteMeal(Meal meal);

    LiveData<List<Meal>> getAllMealsFav(String userID);

    LiveData<List<Meal>> getAllMealsPlan(String userID, String date);

    LiveData<List<Meal>> getAllMealDetails(int mealId);

}
