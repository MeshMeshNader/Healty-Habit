package com.mohamednader.healthyhabit.Area.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface AreaPresenterInterface {

    public void getMealsByArea(String category);
    public void addMealToFav(Meal meal);

}
