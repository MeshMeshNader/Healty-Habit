package com.mohamednader.healthyhabit.Home.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamednader.healthyhabit.Adapters.AreasAdapter;
import com.mohamednader.healthyhabit.Adapters.CategoriesAdapter;
import com.mohamednader.healthyhabit.Adapters.OnAreaClickListener;
import com.mohamednader.healthyhabit.Adapters.OnCategoryClickListener;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Adapters.SwipeMealAdapter;
import com.mohamednader.healthyhabit.Area.View.AreaActivity;
import com.mohamednader.healthyhabit.Category.View.CategoryActivity;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Favorites.View.FavActivity;
import com.mohamednader.healthyhabit.Home.Presenter.HomePresenter;
import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsActivity;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.View.SearchActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class HomeActivity extends AppCompatActivity implements HomeViewInterface,
        OnAreaClickListener, OnCategoryClickListener, OnMealClickListener, SwipeStack.SwipeStackListener {

    public static final String EXTRA_MEAL_ID = "mealID";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_AREA = "area";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    private final String TAG = "HomeActivity_TAG";
    RecyclerView recyclerViewAreas, recyclerViewCategories;
    AreasAdapter areasAdapter;
    CardView searchCard;
    CategoriesAdapter categoriesAdapter;
    SwipeMealAdapter swipeMealAdapter;
    SwipeStack swipeStackView;
    List<Meal> stackMeals;
    int stackCounter = 0;
    private HomePresenter homePresenter;
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.i(TAG, "onCreate: ");

        initViews();
        recyclerViewConfig();

        homePresenter = new HomePresenter(this,
                Repository.getInstance(this, ApiClient.getInstance(), ConcreteLocalSource.getInstance(this)));

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
        for (int i = 0; i < 10; i++) {
            homePresenter.getRandomMeal();
            stackCounter++;
        }
    }

    private void initViews() {

        swipeStackView = findViewById(R.id.swipe_stack_view);
        swipeStackView.setListener(this);
        stackMeals = new ArrayList<>();
        recyclerViewAreas = findViewById(R.id.viewPagerHeader);
        recyclerViewCategories = findViewById(R.id.recyclerCategory);
        searchCard = findViewById(R.id.cardSearch);
        searchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


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


        swipeMealAdapter = new SwipeMealAdapter(this, new ArrayList<>(), this);


    }

    @Override
    public void showMealsByLetterFilter(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showMealsByLetterFilter: " + list.get(i).getStrMeal());
    }

    @Override
    public void showRandomMeal(List<Meal> list) {
        stackMeals.add(list.get(0));
        Log.i(TAG, "showRandomMeal: " + stackCounter);
        if (stackCounter == 10) {
            swipeMealAdapter.setList(stackMeals);
            swipeStackView.setAdapter(swipeMealAdapter);
        }
    }


    @Override
    public void showListAreasNames(List<Meal> list) {
        for (int i = 0; i < list.size(); i++)
            Log.i(TAG, "showListAreasNames: " + list.get(i).getStrArea());
        areasAdapter.setList(list);
        areasAdapter.notifyDataSetChanged();
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
    public void onAreaClick(List<Meal> list, int pos) {
        Intent intent = new Intent(this, FavActivity.class);
        intent.putExtra(EXTRA_AREA, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(List<Category> list, int pos) {

        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);

    }

    @Override
    public void onMealClick(int mealID) {
        Intent intent = new Intent(this, MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);
    }

    @Override
    public void onDeleteMealClick(Meal meal) {

    }

    @Override
    public void onFavMealClick(Meal meal) {
        this.meal = meal;
        homePresenter.addMealToFav(meal);
    }

    @Override
    public void onAddedToFavSuccessfully() {
        Toast.makeText(this, meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onStackEmpty() {
        swipeStackView.resetStack();

    }
}