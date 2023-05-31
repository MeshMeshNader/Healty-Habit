package com.mohamednader.healthyhabit.Network;

public interface RemoteSource {
    public void startCallToGetMealsByFirstLetter(NetworkDelegateAPI networkDelegate, Character character);

    public void startCallToGetRandomMeal(NetworkDelegateAPI networkDelegate);

    public void startCallToGetMealDetailsByID(NetworkDelegateAPI networkDelegate, int id);

    public void startCallToGetListCategoriesNames(NetworkDelegateAPI networkDelegate);

    public void startCallToGetListAreasNames(NetworkDelegateAPI networkDelegate);

    public void startCallToGetListIngredientsNames(NetworkDelegateAPI networkDelegate);

    public void startCallToGetMealsByCategory(NetworkDelegateAPI networkDelegate, String category);

    public void startCallToGetMealsByArea(NetworkDelegateAPI networkDelegate, String area);

    public void startCallToGetMealsByIngredient(NetworkDelegateAPI networkDelegate, String ingredient);

    public void startCallToGetListCategoriesDetails(NetworkDelegateAPI networkDelegate);

    // public void startCallFirebaseLogin(NetworkDelegateAPI networkDelegate, String email, String password);


}
