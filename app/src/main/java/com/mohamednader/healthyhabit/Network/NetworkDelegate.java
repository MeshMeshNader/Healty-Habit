package com.mohamednader.healthyhabit.Network;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface NetworkDelegate {
    public void onSuccessResponse(List<Meal> list);
    public void onFailureResponse(String errorMsg);
}

