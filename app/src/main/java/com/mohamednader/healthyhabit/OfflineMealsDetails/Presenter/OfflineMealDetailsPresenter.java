package com.mohamednader.healthyhabit.OfflineMealsDetails.Presenter;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;
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
         repositoryInterface.startCallToGetMealDetailsByID(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
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


    @Override
    public void deleteMealFromFav(Meal meal) {
        repositoryInterface.deleteMeal(meal);
    }

}
