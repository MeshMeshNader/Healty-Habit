package com.mohamednader.healthyhabit.Home.View;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface HomeViewInterface {

    public void showMealsByLetterFilter(List<Meal> list);

    public void showRandomMeal(List<Meal> list);

    public void showListAreasNames(List<Meal> list);

    public void showListCategoriesDetails(List<Category> list);

    public void addToFavMeal(Meal meal);

    void showLoading();

    void hideLoading();

    public void onAddedToFavSuccessfully();

}
