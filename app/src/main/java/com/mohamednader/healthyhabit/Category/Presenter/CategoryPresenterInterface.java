package com.mohamednader.healthyhabit.Category.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface CategoryPresenterInterface {

    public void getMealsByCategory(String category);

    public void addMealToFav(Meal meal);

    public void getMealDetailsByID(int id);
}
