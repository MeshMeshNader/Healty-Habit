package com.mohamednader.healthyhabit.Search.Presenter.SearchResponse;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface SearchFragmentResponsePresenterInterface {

    public void getMealsByCategory(String category);

    public void getMealsByArea(String area);

    public void getMealsByIngredient(String ingredient);

    public void addMealToFav(Meal meal);

}
