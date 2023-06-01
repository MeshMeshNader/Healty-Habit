package com.mohamednader.healthyhabit.OfflineMealsDetails.Presenter;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.OfflineMealsDetails.View.OfflineMealDetailsViewInterface;

import java.util.List;

public class OfflineMealDetailsPresenter implements OfflineMealDetailsPresenterInterface {

    private OfflineMealDetailsViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public OfflineMealDetailsPresenter(OfflineMealDetailsViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getMealDetailsByID(int id) {
        LiveData<List<Meal>> meals = repositoryInterface.getAllMealDetails(id);

        viewInterface.showMealDetailsByID(meals);


    }


    @Override
    public void deleteMealFromFav(Meal meal) {
        repositoryInterface.deleteMeal(meal);
    }

}
