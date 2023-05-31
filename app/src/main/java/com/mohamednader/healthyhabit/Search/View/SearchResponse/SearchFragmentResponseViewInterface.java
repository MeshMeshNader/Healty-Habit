package com.mohamednader.healthyhabit.Search.View.SearchResponse;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface SearchFragmentResponseViewInterface {

    public void showMealsByArea(List<Meal> list);

    public void showMealsByIngredient(List<Meal> list);

    public void showMealsByCategory(List<Meal> list);
    
    public void showLoading();

    public void hideLoading();

    public void onAddedToFavSuccessfully();

}
