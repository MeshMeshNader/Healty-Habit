package com.mohamednader.healthyhabit.Models;

import android.content.Context;

import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.Network.NetworkDelegate;
import com.mohamednader.healthyhabit.Network.RemoteSource;

public class Repository implements RepositoryInterface{

    Context context;
    RemoteSource remoteSource;
    private static Repository repo = null;

    private Repository(Context context, RemoteSource remoteSource) {
        this.context = context;
        this.remoteSource = remoteSource;
    }

    public static Repository getInstance(Context context, RemoteSource remoteSource) {
        if (repo == null) {
            repo = new Repository(context, remoteSource);
        }
        return repo;
    }


    @Override
    public void startCallToGetMealsByFirstLetter(NetworkDelegate networkDelegate, Character character) {
        remoteSource.startCallToGetMealsByFirstLetter(networkDelegate, character);
    }

    @Override
    public void startCallToGetRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.startCallToGetRandomMeal(networkDelegate);
    }

    @Override
    public void startCallToGetMealDetailsByID(NetworkDelegate networkDelegate, int id) {
        remoteSource.startCallToGetMealDetailsByID(networkDelegate, id);
    }

    @Override
    public void startCallToGetListCategoriesNames(NetworkDelegate networkDelegate) {
        remoteSource.startCallToGetListCategoriesNames(networkDelegate);
    }

    @Override
    public void startCallToGetListAreasNames(NetworkDelegate networkDelegate) {
        remoteSource.startCallToGetListAreasNames(networkDelegate);
    }

    @Override
    public void startCallToGetListIngredientsNames(NetworkDelegate networkDelegate) {
        remoteSource.startCallToGetListIngredientsNames(networkDelegate);
    }

    @Override
    public void startCallToGetMealsByCategory(NetworkDelegate networkDelegate, String category) {
        remoteSource.startCallToGetMealsByCategory(networkDelegate, category);
    }

    @Override
    public void startCallToGetMealsByArea(NetworkDelegate networkDelegate, String area) {
        remoteSource.startCallToGetMealsByArea(networkDelegate,area);
    }

    @Override
    public void startCallToGetMealsByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        remoteSource.startCallToGetMealsByIngredient(networkDelegate, ingredient);
    }

    @Override
    public void startCallToGetListCategoriesDetails(NetworkDelegate networkDelegate) {
        remoteSource.startCallToGetListCategoriesDetails(networkDelegate);
    }
}
