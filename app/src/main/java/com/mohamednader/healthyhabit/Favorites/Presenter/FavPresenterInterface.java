package com.mohamednader.healthyhabit.Favorites.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface FavPresenterInterface {

    public void getStoredMeals();
    public void deleteMealFromFav(Meal meal);
}
