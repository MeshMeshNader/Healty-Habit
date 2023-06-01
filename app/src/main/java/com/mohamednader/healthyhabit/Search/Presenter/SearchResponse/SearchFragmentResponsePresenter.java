package com.mohamednader.healthyhabit.Search.Presenter.SearchResponse;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;
import com.mohamednader.healthyhabit.Search.View.SearchResponse.SearchFragmentResponseViewInterface;

import java.util.List;

public class SearchFragmentResponsePresenter implements SearchFragmentResponsePresenterInterface {


    private SearchFragmentResponseViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;

    public SearchFragmentResponsePresenter(SearchFragmentResponseViewInterface viewInterface, RepositoryInterface repositoryInterface) {
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
    public void addMealToFav(Meal meal) {

        repositoryInterface.insertMeal(meal);
        viewInterface.onAddedToFavSuccessfully();
    }

}
