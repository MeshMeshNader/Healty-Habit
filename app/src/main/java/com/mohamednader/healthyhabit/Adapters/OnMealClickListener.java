package com.mohamednader.healthyhabit.Adapters;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface OnMealClickListener {

    public void onMealClick(int mealID);

    public void onDeleteMealClick(Meal meal);

    public void onFavMealClick(Meal meal);

}
