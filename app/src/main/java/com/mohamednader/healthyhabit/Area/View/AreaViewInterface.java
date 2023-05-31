package com.mohamednader.healthyhabit.Area.View;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface AreaViewInterface {

    public void showMealsByArea(List<Meal> list);

    public void showLoading();

    public void hideLoading();


}
