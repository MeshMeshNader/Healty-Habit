package com.mohamednader.healthyhabit.Home.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
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

public class HomeFragment extends Fragment implements HomeViewInterface,
        OnAreaClickListener, OnCategoryClickListener, OnMealClickListener, SwipeStack.SwipeStackListener {

    public static final String EXTRA_MEAL_ID = "mealID";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_AREA = "area";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    private final String TAG = "HomeFragment_TAG";
    private RecyclerView recyclerViewAreas;
    private RecyclerView recyclerViewCategories;
    private AreasAdapter areasAdapter;
    private CardView searchCard;
    private CategoriesAdapter categoriesAdapter;
    private SwipeMealAdapter swipeMealAdapter;
    private SwipeStack swipeStackView;
    private List<Meal> stackMeals;
    private int stackCounter = 0;
    private HomePresenter homePresenter;
    private Meal meal;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.i(TAG, "onCreateView: ");

        initViews();
        recyclerViewConfig();

        homePresenter = new HomePresenter(this,
                Repository.getInstance(getActivity(), ApiClient.getInstance(), ConcreteLocalSource.getInstance(getActivity())));

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

        return view;
    }

    private void initViews() {
        swipeStackView = view.findViewById(R.id.swipe_stack_view);
        swipeStackView.setListener(this);
        stackMeals = new ArrayList<>();
        recyclerViewAreas = view.findViewById(R.id.viewPagerHeader);
        recyclerViewCategories = view.findViewById(R.id.recyclerCategory);
        searchCard = view.findViewById(R.id.cardSearch);
        searchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewConfig() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewAreas.setHasFixedSize(true);
        recyclerViewAreas.setLayoutManager(linearLayoutManager);
        areasAdapter = new AreasAdapter(getActivity(), new ArrayList<>(), this);
        recyclerViewAreas.setAdapter(areasAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerViewCategories.setLayoutManager(layoutManager);
        recyclerViewCategories.setNestedScrollingEnabled(true);
        categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<>(), this);
        recyclerViewCategories.setAdapter(categoriesAdapter);

        swipeMealAdapter = new SwipeMealAdapter(getActivity(), new ArrayList<>(), this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Add any further view setup or initialization logic here
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
        view.findViewById(R.id.shimmerArea).setVisibility(View.VISIBLE);
        view.findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        view.findViewById(R.id.shimmerArea).setVisibility(View.GONE);
        view.findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void onAreaClick(List<Meal> list, int pos) {
        Intent intent = new Intent(getActivity(), AreaActivity.class);
        intent.putExtra(EXTRA_AREA, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(List<Category> list, int pos) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, (Serializable) list);
        intent.putExtra(EXTRA_POSITION, pos);
        startActivity(intent);
    }

    @Override
    public void onMealClick(int mealID) {
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);
    }

    @Override
    public void onDeleteMealClick(Meal meal) {
        // Handle delete meal click event here
    }

    @Override
    public void onFavMealClick(Meal meal) {
        this.meal = meal;
        homePresenter.addMealToFav(meal);
    }

    @Override
    public void onAddedToFavSuccessfully() {
        Toast.makeText(getActivity(), meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        // Handle view swiped to left event here
    }

    @Override
    public void onViewSwipedToRight(int position) {
        // Handle view swiped to right event here
    }

    @Override
    public void onStackEmpty() {
        swipeStackView.resetStack();
    }
}
