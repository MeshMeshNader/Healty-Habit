package com.mohamednader.healthyhabit.MealDetails.View;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface MealDetailsViewInterface {

    public void showMealDetailsByID(List<Meal> list);

    public void showLoading();

    public void hideLoading();


        public void onAddedToFavSuccessfully();


}
