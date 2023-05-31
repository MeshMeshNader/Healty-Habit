package com.mohamednader.healthyhabit.Favorites.View;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface FavViewInterface {

    public void showAllStoredMeals(LiveData<List<Meal>> mealsList);

    public void onDeletedFromFavSuccessfully();

}
