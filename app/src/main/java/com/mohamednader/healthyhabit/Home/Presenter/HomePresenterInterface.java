package com.mohamednader.healthyhabit.Home.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

public interface HomePresenterInterface {

    public void getMealsByLetterFilter(Character character);

    public void getRandomMeal();

    public void getListAreasNames();

    public void getListCategoriesDetails();

    public void addMealToFav(Meal meal);

    public void getMealDetailsByID(int id);

}
