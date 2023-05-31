package com.mohamednader.healthyhabit.Search.View.SearchRequest;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public interface SearchFragmentRequestViewInterface {

    public void showListCategoriesNames(List<Meal> list);

    public void showListAreasNames(List<Meal> list);

    public void showListIngredientsNames(List<Meal> list);

    public void showLoading();

    public void hideLoading();

    public void upDateList(String newText);



}
