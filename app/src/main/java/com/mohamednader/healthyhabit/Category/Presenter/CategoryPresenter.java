package com.mohamednader.healthyhabit.Category.Presenter;

import com.mohamednader.healthyhabit.Category.View.CategoryViewInterface;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterInterface {


    private CategoryViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public CategoryPresenter(CategoryViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public void getMealDetailsByID(int id) {

        repositoryInterface.startCallToGetMealDetailsByID(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {

                viewInterface.addToFavMeal(list.get(0));
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
    public void getMealsByCategory(String category) {

        viewInterface.showLoading();
        repositoryInterface.startCallToGetMealsByCategory(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.hideLoading();
                viewInterface.showMealsByCategory(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, category);
    }

    @Override
    public void addMealToFav(Meal meal) {

        repositoryInterface.insertMeal(meal);
        viewInterface.onAddedToFavSuccessfully();
    }
}
