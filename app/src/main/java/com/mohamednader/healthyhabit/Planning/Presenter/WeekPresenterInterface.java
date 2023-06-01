package com.mohamednader.healthyhabit.Planning.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface WeekPresenterInterface {

    public void getStoredMeals(String userID, String date);

    public void deleteMealFromFav(Meal meal);

}
