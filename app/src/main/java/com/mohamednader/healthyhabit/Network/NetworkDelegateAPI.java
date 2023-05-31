package com.mohamednader.healthyhabit.Network;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface NetworkDelegateAPI {
    public void onSuccessResponseMeal(List<Meal> list);

    public void onSuccessResponseCategory(List<Category> list);

    public void onFailureResponse(String errorMsg);
}

