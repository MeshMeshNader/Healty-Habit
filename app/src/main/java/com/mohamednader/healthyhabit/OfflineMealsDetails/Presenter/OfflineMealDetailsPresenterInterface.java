package com.mohamednader.healthyhabit.OfflineMealsDetails.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface OfflineMealDetailsPresenterInterface {

    public void getMealDetailsByID(int id);
    public void deleteMealFromFav(Meal meal);

}
