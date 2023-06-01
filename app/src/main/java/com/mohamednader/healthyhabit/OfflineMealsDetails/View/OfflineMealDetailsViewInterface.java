package com.mohamednader.healthyhabit.OfflineMealsDetails.View;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface OfflineMealDetailsViewInterface {

    public void showMealDetailsByID(LiveData<List<Meal>> meal);

}
