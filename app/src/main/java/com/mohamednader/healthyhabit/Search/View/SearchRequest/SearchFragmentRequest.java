package com.mohamednader.healthyhabit.Search.View.SearchRequest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.Presenter.SearchRequest.SearchFragmentRequestPresenter;
import com.mohamednader.healthyhabit.Search.Presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragmentRequest extends Fragment implements SearchFragmentRequestViewInterface{

    View view;
    ProgressBar progressBar;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> searchItems;
    SearchFragmentRequestPresenter searchFragmentRequestPresenter;
    SearchPresenter searchPresenter;

    public SearchFragmentRequest(SearchPresenter searchPresenter) {
        // Required empty public constructor
        this.searchPresenter = searchPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_request, container, false);

        initViews();

        return view;

    }

    private void initViews() {

        searchItems = new ArrayList<>();
        listView = view.findViewById(R.id.list_view);
        progressBar = view.findViewById(R.id.progressBar);
        arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_list_search, searchItems);

        searchFragmentRequestPresenter = new SearchFragmentRequestPresenter(this,
                Repository.getInstance(getContext(), ApiClient.getInstance()), searchPresenter);

        searchFragmentRequestPresenter.getListAreasNames();
        searchFragmentRequestPresenter.getListIngredientsNames();
        searchFragmentRequestPresenter.getListCategoriesNames();
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) arrayAdapter.getItem(position);
                searchFragmentRequestPresenter.showResponseName(selectedItem);
            }
        });

    }


    @Override
    public void showListCategoriesNames(List<Meal> list) {
        for (Meal meal : list) {
            searchItems.add(meal.getStrCategory());
        }

        arrayAdapter.notifyDataSetChanged();


    }

    @Override
    public void showListAreasNames(List<Meal> list) {
        for (Meal meal : list) {
            searchItems.add(meal.getStrArea());
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListIngredientsNames(List<Meal> list) {
        for (Meal meal : list) {
            searchItems.add(meal.getStrIngredientItem());
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void upDateList(String newText) {
        arrayAdapter.getFilter().filter(newText);
        arrayAdapter.notifyDataSetChanged();
    }



}