package com.mohamednader.healthyhabit.Search.View.SearchResponse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.Presenter.SearchResponse.SearchFragmentResponsePresenter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragmentResponse extends Fragment implements SearchFragmentResponseViewInterface, OnMealClickListener {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MealsAdapter mealsAdapter;
    SearchFragmentResponsePresenter searchFragmentResponsePresenter;
    List<Meal> resultItems;
    String key = "";

    public SearchFragmentResponse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_response, container, false);
        searchFragmentResponsePresenter = new SearchFragmentResponsePresenter(this,
                Repository.getInstance(getActivity(), ApiClient.getInstance()));
        initViews();
        recyclerViewConfig();
        return view;
    }


    private void initViews() {

        resultItems = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

    }

    private void recyclerViewConfig() {
        mealsAdapter = new MealsAdapter(getActivity(), new ArrayList<>(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(mealsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            key = getArguments().getString("EXTRA_SEARCH");

            searchFragmentResponsePresenter.getMealsByArea(key);
            searchFragmentResponsePresenter.getMealsByIngredient(key);
            searchFragmentResponsePresenter.getMealsByCategory(key);

        }


    }


    @Override
    public void showMealsByArea(List<Meal> list) {
        if (list != null)
            resultItems.addAll(list);
    }

    @Override
    public void showMealsByIngredient(List<Meal> list) {
        if (list != null)
            resultItems.addAll(list);
    }

    @Override
    public void showMealsByCategory(List<Meal> list) {
        if (list != null)
            resultItems.addAll(list);

        mealsAdapter.setList(resultItems);
        mealsAdapter.notifyDataSetChanged();
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onMealClick(int mealID) {

    }

    @Override
    public void onFavMealClick(Meal meal) {

    }
}