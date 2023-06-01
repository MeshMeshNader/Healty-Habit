package com.mohamednader.healthyhabit.Search.View.SearchResponse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsActivity;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.Planning.Presenter.CalendarUtils;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.Presenter.SearchResponse.SearchFragmentResponsePresenter;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class SearchFragmentResponse extends Fragment implements SearchFragmentResponseViewInterface, OnMealClickListener {

    public static final String EXTRA_MEAL_ID = "mealID";
    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MealsAdapter mealsAdapter;
    SearchFragmentResponsePresenter searchFragmentResponsePresenter;
    List<Meal> resultItems;
    String key = "";
    Meal meal;
    private boolean plan = false;

    public SearchFragmentResponse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_response, container, false);
        searchFragmentResponsePresenter = new SearchFragmentResponsePresenter(this,
                Repository.getInstance(getActivity(), ApiClient.getInstance(), ConcreteLocalSource.getInstance(getActivity())));
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
        mealsAdapter = new MealsAdapter(getActivity(), new ArrayList<>(), this, "Fav");
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(mealsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            key = getArguments().getString("EXTRA_SEARCH");
            plan = getArguments().getBoolean("EXTRA_PLAN");


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
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);
    }


    @Override
    public void onDeleteMealClick(Meal meal) {

    }

    @Override
    public void addToFavMeal(Meal meal) {
        this.meal = meal;
        if (!plan) {
            meal.setStrIngredient20(Utils.getSp(getActivity())
                    .getString(Utils.UserID, ""));
            searchFragmentResponsePresenter.addMealToFav(meal);
        } else {
            Log.i("TESTINNNNNNG", "onFavMealClick: ");
            meal.setStrMeasure20(CalendarUtils.selectedDate.toString());
            meal.setStrIngredient20(Utils.getSp(getActivity())
                    .getString(Utils.UserID, ""));
            searchFragmentResponsePresenter.addMealToFav(meal);
        }
    }

    @Override
    public void onFavMealClick(Meal meal) {

        searchFragmentResponsePresenter.getMealDetailsByID(Integer.parseInt(meal.getIdMeal()));


    }

    @Override
    public void onAddedToFavSuccessfully() {
        if (!plan)
            Toast.makeText(getActivity(), meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), meal.getStrMeal() + " Added To Your Plan! ", Toast.LENGTH_SHORT).show();

    }

}