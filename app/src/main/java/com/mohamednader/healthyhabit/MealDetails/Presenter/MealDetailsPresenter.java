package com.mohamednader.healthyhabit.MealDetails.Presenter;

import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsViewInterface;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;

import java.util.List;

public class MealDetailsPresenter implements MealDetailsPresenterInterface {

    private MealDetailsViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public MealDetailsPresenter(MealDetailsViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getMealDetailsByID(int id) {
        viewInterface.showLoading();
        repositoryInterface.startCallToGetMealDetailsByID(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.hideLoading();
                viewInterface.showMealDetailsByID(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, id);
    }
}
