package com.mohamednader.healthyhabit.Planning.Presenter;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Planning.View.WeekViewInterface;

public class WeekPresenter implements WeekPresenterInterface {

    WeekViewInterface viewInterface;
    RepositoryInterface repositoryInterface;

    public WeekPresenter(WeekViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getStoredMeals(String userID, String date) {
        viewInterface.showAllStoredMeals(repositoryInterface.getAllMealsPlan(userID, date));
    }

    @Override
    public void deleteMealFromFav(Meal meal) {

        repositoryInterface.deleteMeal(meal);
        viewInterface.onDeletedFromFavSuccessfully();
    }

}
