package com.mohamednader.healthyhabit.Home.Presenter;

import com.mohamednader.healthyhabit.Home.View.HomeViewInterface;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;

import java.util.List;

public class HomePresenter implements HomePresenterInterface {

    private HomeViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public HomePresenter(HomeViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void getMealsByLetterFilter(Character character) {
        repositoryInterface.startCallToGetMealsByFirstLetter(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.showMealsByLetterFilter(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, character);
    }

    @Override
    public void getRandomMeal() {
        repositoryInterface.startCallToGetRandomMeal(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.showRandomMeal(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }


    @Override
    public void getListCategoriesNames() {



        repositoryInterface.startCallToGetListCategoriesNames(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.showListCategoriesNames(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getListAreasNames() {

        viewInterface.showLoading();

        repositoryInterface.startCallToGetListAreasNames(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.hideLoading();
                viewInterface.showListAreasNames(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getListIngredientsNames() {
        repositoryInterface.startCallToGetListIngredientsNames(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.showListIngredientsNames(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getMealsByArea(String area) {
        repositoryInterface.startCallToGetMealsByArea(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
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

    @Override
    public void getMealsByIngredient(String ingredient) {
        repositoryInterface.startCallToGetMealsByIngredient(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.showMealsByIngredient(list);
            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {

            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, ingredient);
    }

    @Override
    public void getListCategoriesDetails() {
        viewInterface.showLoading();
        repositoryInterface.startCallToGetListCategoriesDetails(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {

            }

            @Override
            public void onSuccessResponseCategory(List<Category> list) {
                viewInterface.hideLoading();
                viewInterface.showListCategoriesDetails(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }
}
