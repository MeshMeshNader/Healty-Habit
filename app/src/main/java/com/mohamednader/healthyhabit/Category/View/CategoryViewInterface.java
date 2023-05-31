package com.mohamednader.healthyhabit.Category.View;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface CategoryViewInterface {

    public void showMealsByCategory(List<Meal> list);

    public void showLoading();

    public void hideLoading();

    public void onAddedToFavSuccessfully();
}
