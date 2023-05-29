package com.mohamednader.healthyhabit.Network;

public interface RemoteSource {
    public void startCallToGetMealsByFirstLetter(NetworkDelegate networkDelegate, Character character);

    public void startCallToGetRandomMeal(NetworkDelegate networkDelegate);

    public void startCallToGetMealDetailsByID(NetworkDelegate networkDelegate, int id);

    public void startCallToGetListCategoriesNames(NetworkDelegate networkDelegate);

    public void startCallToGetListAreasNames(NetworkDelegate networkDelegate);

    public void startCallToGetListIngredientsNames(NetworkDelegate networkDelegate);

    public void startCallToGetMealsByCategory(NetworkDelegate networkDelegate, String category);

    public void startCallToGetMealsByArea(NetworkDelegate networkDelegate, String area);

    public void startCallToGetMealsByIngredient(NetworkDelegate networkDelegate, String ingredient);

    public void startCallToGetListCategoriesDetails(NetworkDelegate networkDelegate);
}
