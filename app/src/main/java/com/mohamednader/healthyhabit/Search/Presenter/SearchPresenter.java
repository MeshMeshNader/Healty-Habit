package com.mohamednader.healthyhabit.Search.Presenter;

import com.mohamednader.healthyhabit.Models.RepositoryInterface;
import com.mohamednader.healthyhabit.Search.View.SearchRequest.SearchFragmentRequest;
import com.mohamednader.healthyhabit.Search.View.SearchRequest.SearchFragmentRequestViewInterface;
import com.mohamednader.healthyhabit.Search.View.SearchResponse.SearchFragmentResponseViewInterface;
import com.mohamednader.healthyhabit.Search.View.SearchViewInterface;

public class SearchPresenter implements SearchPresenterInterface {

    private SearchViewInterface viewInterface;
    private SearchFragmentRequestViewInterface searchFragmentRequestViewInterface;
    private RepositoryInterface repositoryInterface;

    public SearchPresenter(SearchViewInterface viewInterface, RepositoryInterface repositoryInterface) {
        this.viewInterface = viewInterface;
        this.repositoryInterface = repositoryInterface;
    }

    public void setSearchFragmentRequest(SearchFragmentRequest searchFragmentRequest) {
        this.searchFragmentRequestViewInterface = searchFragmentRequest;
    }



    @Override
    public void upDateList(String newText) {
        searchFragmentRequestViewInterface.upDateList(newText);
    }

    @Override
    public void sendResponse(String searchResponseName) {
        viewInterface.goToSearchResult(searchResponseName);
    }



}
