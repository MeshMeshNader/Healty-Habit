package com.mohamednader.healthyhabit.Home.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamednader.healthyhabit.Adapters.AreasAdapter;
import com.mohamednader.healthyhabit.Adapters.CategoriesAdapter;
import com.mohamednader.healthyhabit.Adapters.OnAreaClickListener;
import com.mohamednader.healthyhabit.Adapters.OnCategoryClickListener;
import com.mohamednader.healthyhabit.Category.View.CategoryActivity;
import com.mohamednader.healthyhabit.Home.Presenter.HomePresenter;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeViewInterface, OnAreaClickListener, OnCategoryClickListener {

    private final String TAG = "HomeActivity_TAG";
    private HomePresenter homePresenter;

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    RecyclerView recyclerViewAreas, recyclerViewCategories;
    AreasAdapter areasAdapter;
    CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.i(TAG, "onCreate: ");

        initViews();
        recyclerViewConfig();

        homePresenter = new HomePresenter(this,
                Repository.getInstance(this, ApiClient.getInstance()));

//        homePresenter.getMealsByLetterFilter('g');
//        homePresenter.getRandomMeal();
//        homePresenter.getMealDetailsByID(52772);
//        homePresenter.getListCategoriesNames();
        homePresenter.getListAreasNames();
//        homePresenter.getListIngredientsNames();
//        homePresenter.getMealsByCategory("Beef");
//        homePresenter.getMealsByArea("Egyptian");
//        homePresenter.getMealsByIngredient("Chicken");
        homePresenter.getListCategoriesDetails();
    }

    private void initViews() {

        recyclerViewAreas = findViewById(R.id.viewPagerHeader);
        recyclerViewCategories = findViewById(R.id.recyclerCategory);

    }


    private void recyclerViewConfig() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewAreas.setHasFixedSize(true);
        recyclerViewAreas.setLayoutManager(linearLayoutManager);
        areasAdapter = new AreasAdapter(this, new ArrayList<>(), this);
        recyclerViewAreas.setAdapter(areasAdapter);



        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategories.setLayoutManager(layoutManager);
        recyclerViewCategories.setNestedScrollingEnabled(true);
        categoriesAdapter = new CategoriesAdapter(this, new ArrayList<>(), this);
        recyclerViewCategories.setAdapter(categoriesAdapter);

    }

    @Override
    public void showMealsByLetterFilter(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showMealsByLetterFilter: " + list.get(i).getStrMeal());
    }

    @Override
    public void showRandomMeal(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showRandomMeal: " + list.get(i).getStrMeal());
    }

    @Override
    public void showMealDetailsByID(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showMealDetailsByID: " + list.get(i).getStrMeal());
    }

    @Override
    public void showListCategoriesNames(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showListCategoriesNames: " + list.get(i).getStrCategory());
    }

    @Override
    public void showListAreasNames(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showListAreasNames: " + list.get(i).getStrArea());
        areasAdapter.setList(list);
        areasAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListIngredientsNames(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showListIngredientsNames: " + list.get(i).getStrIngredientItem());
    }


    @Override
    public void showMealsByArea(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showMealsByArea: " + list.get(i).getStrMeal());
    }

    @Override
    public void showMealsByIngredient(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showMealsByIngredient: " + list.get(i).getStrMeal());
    }

    @Override
    public void showListCategoriesDetails(List<Category> list) {
        categoriesAdapter.setList(list);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerArea).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerArea).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }


    @Override
    public void onAreaClick(String areaName) {
        Toast.makeText(this, "You Clicked " + areaName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(List<Category> list, int pos) {

        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);

    }
}