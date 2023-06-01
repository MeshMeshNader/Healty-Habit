package com.mohamednader.healthyhabit.Favorites.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface FavPresenterInterface {

    public void getStoredMeals(String userID);

    public void deleteMealFromFav(Meal meal);
}
