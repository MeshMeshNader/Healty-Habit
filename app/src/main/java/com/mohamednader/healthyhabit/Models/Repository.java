package com.mohamednader.healthyhabit.Models;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Database.LocalSource;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;
import com.mohamednader.healthyhabit.Network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface {

    //For Google Login and SignUp
    private static final int RC_SIGN_IN = 100;
    private static Repository repo = null;
    Context context;
    RemoteSource remoteSource;
    LocalSource localSource;

    private Repository(Context context, RemoteSource remoteSource, LocalSource localSource) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSource = localSource;


    }

    public static Repository getInstance(Context context, RemoteSource remoteSource, LocalSource localSource) {
        if (repo == null) {
            repo = new Repository(context, remoteSource, localSource);
        }
        return repo;
    }


    @Override
    public void startCallToGetMealsByFirstLetter(NetworkDelegateAPI networkDelegate, Character character) {
        remoteSource.startCallToGetMealsByFirstLetter(networkDelegate, character);
    }

    @Override
    public void startCallToGetRandomMeal(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetRandomMeal(networkDelegate);
    }

    @Override
    public void startCallToGetMealDetailsByID(NetworkDelegateAPI networkDelegate, int id) {
        remoteSource.startCallToGetMealDetailsByID(networkDelegate, id);
    }

    @Override
    public void startCallToGetListCategoriesNames(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListCategoriesNames(networkDelegate);
    }

    @Override
    public void startCallToGetListAreasNames(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListAreasNames(networkDelegate);
    }

    @Override
    public void startCallToGetListIngredientsNames(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListIngredientsNames(networkDelegate);
    }

    @Override
    public void startCallToGetMealsByCategory(NetworkDelegateAPI networkDelegate, String category) {
        remoteSource.startCallToGetMealsByCategory(networkDelegate, category);
    }

    @Override
    public void startCallToGetMealsByArea(NetworkDelegateAPI networkDelegate, String area) {
        remoteSource.startCallToGetMealsByArea(networkDelegate, area);
    }

    @Override
    public void startCallToGetMealsByIngredient(NetworkDelegateAPI networkDelegate, String ingredient) {
        remoteSource.startCallToGetMealsByIngredient(networkDelegate, ingredient);
    }

    @Override
    public void startCallToGetListCategoriesDetails(NetworkDelegateAPI networkDelegate) {
        remoteSource.startCallToGetListCategoriesDetails(networkDelegate);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return localSource.getStoredMeals();
    }

    @Override
    public LiveData<List<Meal>> getAllMealsFav(String userID) {
        return localSource.getAllMealsFav(userID);
    }

    @Override
    public LiveData<List<Meal>> getAllMealsPlan(String userID, String date) {
        return localSource.getAllMealsPlan(userID, date);
    }

    @Override
    public LiveData<List<Meal>> getAllMealDetails(int mealId) {
        return localSource.getAllMealDetails(mealId);
    }

    @Override
    public void insertMeal(Meal meal) {
        localSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.deleteMeal(meal);
    }


}
