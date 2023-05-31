package com.mohamednader.healthyhabit.Favorites.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Favorites.Presenter.FavPresenter;
import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsActivity;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.OfflineMealsDetails.View.OfflineMealDetailsActivity;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.Presenter.SearchResponse.SearchFragmentResponsePresenter;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements FavViewInterface, OnMealClickListener {

    View view;
    RecyclerView recyclerView;
    MealsAdapter favAdapter;
    FavPresenter favPresenter;
    List<Meal> resultItems;
    String key = "";
    Meal meal;
    public static final String EXTRA_MEAL_ID = "mealID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_avtivity);

        favPresenter = new FavPresenter(this,
                Repository.getInstance(this, ApiClient.getInstance(), ConcreteLocalSource.getInstance(this)));
        initViews();
        recyclerViewConfig();

        favPresenter.getStoredMeals();
    }

    private void initViews() {

        resultItems = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

    }

    private void recyclerViewConfig() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favAdapter = new MealsAdapter(this, new ArrayList<>(), this, "Del");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(favAdapter);
    }

    @Override
    public void onMealClick(int mealID) {
        Intent intent = new Intent(this, OfflineMealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);
    }

    @Override
    public void showAllStoredMeals(LiveData<List<Meal>> mealsList) {
        mealsList.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> productList) {
                favAdapter.setList(productList);
                favAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDeleteMealClick(Meal meal) {
        this.meal = meal;
        favPresenter.deleteMealFromFav(meal);
    }

    @Override
    public void onFavMealClick(Meal meal) {

    }

    @Override
    public void onDeletedFromFavSuccessfully() {
        Toast.makeText(this, meal.getStrMeal() + " Deleted !! ", Toast.LENGTH_SHORT).show();
    }

}