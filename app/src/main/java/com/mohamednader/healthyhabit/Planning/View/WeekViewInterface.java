package com.mohamednader.healthyhabit.Planning.View;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface WeekViewInterface {
    public void showAllStoredMeals(LiveData<List<Meal>> mealsList);

    public void onDeletedFromFavSuccessfully();
}
