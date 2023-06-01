package com.mohamednader.healthyhabit.MealDetails.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface MealDetailsPresenterInterface {

    public void getMealDetailsByID(int id);

    public void addMealToFav(Meal meal);

}
