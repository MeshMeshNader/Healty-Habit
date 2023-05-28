package com.mohamednader.healthyhabit.Home.Presenter;

import com.mohamednader.healthyhabit.Home.View.HomeViewInterface;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegate;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkDelegate {

    private HomeViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public HomePresenter(HomeViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void onSuccessResponse(List<Meal> list) {
        viewInterface.showMealsByLetterFilter(list);
    }

    @Override
    public void onFailureResponse(String errorMsg) {

    }

    @Override
    public void getMealsByLetterFilter(Character character) {
        repositoryInterface.startCallToGetMealsByFirstLetter(this, character);
    }
}
