package com.mohamednader.healthyhabit.Home.Presenter;

public interface HomePresenterInterface {

    public void getMealsByLetterFilter(Character character);
    public void getRandomMeal();
    public void getListCategoriesNames();
    public void getListAreasNames();
    public void getListIngredientsNames();
    public void getMealsByArea(String area);
    public void getMealsByIngredient(String ingredient);
    public void getListCategoriesDetails();

}
