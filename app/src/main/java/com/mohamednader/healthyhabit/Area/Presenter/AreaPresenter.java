package com.mohamednader.healthyhabit.Area.Presenter;

import com.mohamednader.healthyhabit.Area.View.AreaViewInterface;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;

import java.util.List;

public class AreaPresenter implements AreaPresenterInterface {


    private AreaViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public AreaPresenter(AreaViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void getMealsByArea(String area) {

        viewInterface.showLoading();
        repositoryInterface.startCallToGetMealsByArea(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.hideLoading();
                viewInterface.showMealsByArea(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, area);
    }

}
