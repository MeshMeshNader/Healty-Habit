package com.mohamednader.healthyhabit.Home.Presenter;

import com.mohamednader.healthyhabit.Home.View.HomeViewInterface;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegate;

import java.util.List;

public class HomePresenter implements HomePresenterInterface {

    private HomeViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public HomePresenter(HomeViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }


//    @Override
//    public void onSuccessResponse(List<Meal> list) {
//        viewInterface.showMealsByLetterFilter(list);
//    }
//
//    @Override
//    public void onFailureResponse(String errorMsg) {
//
//    }

    @Override
    public void getMealsByLetterFilter(Character character) {
        repositoryInterface.startCallToGetMealsByFirstLetter(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showMealsByLetterFilter(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, character);
    }

    @Override
    public void getRandomMeal() {
        repositoryInterface.startCallToGetRandomMeal(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showRandomMeal(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getMealDetailsByID(int id) {
        repositoryInterface.startCallToGetMealDetailsByID(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showMealDetailsByID(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, id);
    }

    @Override
    public void getListCategoriesNames() {
        repositoryInterface.startCallToGetListCategoriesNames(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showListCategoriesNames(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getListAreasNames() {
        repositoryInterface.startCallToGetListAreasNames(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showListAreasNames(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getListIngredientsNames() {
        repositoryInterface.startCallToGetListIngredientsNames(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showListIngredientsNames(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }

    @Override
    public void getMealsByCategory(String category) {
        repositoryInterface.startCallToGetMealsByCategory(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showMealsByCategory(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, category);
    }

    @Override
    public void getMealsByArea(String area) {
        repositoryInterface.startCallToGetMealsByArea(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showMealsByArea(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, area);
    }

    @Override
    public void getMealsByIngredient(String ingredient) {
        repositoryInterface.startCallToGetMealsByIngredient(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                viewInterface.showMealsByIngredient(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        }, ingredient);
    }

    @Override
    public void getListCategoriesDetails() {
        repositoryInterface.startCallToGetListCategoriesDetails(new NetworkDelegate() {
            @Override
            public void onSuccessResponse(List<Meal> list) {
                //viewInterface.showListCategoriesDetails(list);
            }

            @Override
            public void onFailureResponse(String errorMsg) {

            }
        });
    }
}
