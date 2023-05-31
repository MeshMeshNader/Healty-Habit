package com.mohamednader.healthyhabit.Search.Presenter.SearchRequest;

import android.util.Log;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Network.NetworkDelegateAPI;
import com.mohamednader.healthyhabit.Search.Presenter.SearchPresenterInterface;
import com.mohamednader.healthyhabit.Search.View.SearchRequest.SearchFragmentRequestViewInterface;

import java.util.List;

public class SearchFragmentRequestPresenter implements SearchFragmentRequestPresenterInterface {

    private SearchFragmentRequestViewInterface viewInterface;
    private RepositoryInterface repositoryInterface;
    private SearchPresenterInterface searchPresenterInterface;

    public SearchFragmentRequestPresenter(SearchFragmentRequestViewInterface viewInterface, RepositoryInterface repositoryInterface
            , SearchPresenterInterface searchPresenterInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
        this.searchPresenterInterface = searchPresenterInterface;
    }


    @Override
    public void getListCategoriesNames() {


        repositoryInterface.startCallToGetListCategoriesNames(new NetworkDelegateAPI() {
            @Override
            public void onSuccessResponseMeal(List<Meal> list) {
                viewInterface.showListCategoriesNames(list);
                viewInterface.hideLoading();
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
    public void showResponseName(String responseName) {
        searchPresenterInterface.sendResponse(responseName);
    }


}
