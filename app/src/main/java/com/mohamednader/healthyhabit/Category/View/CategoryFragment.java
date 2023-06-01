package com.mohamednader.healthyhabit.Category.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Category.Presenter.CategoryPresenter;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsActivity;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements CategoryViewInterface, OnMealClickListener {

    public static final String EXTRA_MEAL_ID = "mealID";
    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageCategory, imageCategoryBg;
    TextView textCategory;
    MealsAdapter mealsAdapter;
    CategoryPresenter categoryPresenter;
    CardView categoryCardView;
    AlertDialog.Builder descDialog;
    Meal meal;
    TextView emptyMsg;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        initViews();
        recyclerViewConfig();
        return view;

    }

    private void initViews() {
        emptyMsg = view.findViewById(R.id.empty_msg);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        imageCategory = view.findViewById(R.id.imageCategory);
        imageCategoryBg = view.findViewById(R.id.imageCategoryBg);
        textCategory = view.findViewById(R.id.textCategory);
        categoryCardView = view.findViewById(R.id.cardCategory);

        categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descDialog.setPositiveButton("CLOSE", (dialog, which) -> dialog.dismiss());
                descDialog.show();
            }
        });

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
            emptyMsg.setVisibility(View.GONE);
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Glide.with(getActivity())
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategory);
            Glide.with(getActivity())
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategoryBg);
            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            categoryPresenter = new CategoryPresenter(this,
                    Repository.getInstance(getActivity(), ApiClient.getInstance(), ConcreteLocalSource.getInstance(getActivity())));

            categoryPresenter.getMealsByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }else{
                emptyMsg.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showMealsByCategory(List<Meal> list) {

        mealsAdapter.setList(list);
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
    public void addToFavMeal(Meal meal) {
        this.meal = meal;
        meal.setStrIngredient20(Utils.getSp(getActivity())
                .getString(Utils.UserID, ""));
        categoryPresenter.addMealToFav(meal);
    }

    @Override
    public void onMealClick(int mealID) {
        //Toast.makeText(getActivity(), "You Clicked " + mealID, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);

    }

    @Override
    public void onFavMealClick(Meal meal) {
        categoryPresenter.getMealDetailsByID(Integer.parseInt(meal.getIdMeal()));
    }

    @Override
    public void onDeleteMealClick(Meal meal) {

    }

    @Override
    public void onAddedToFavSuccessfully() {
        Toast.makeText(getActivity(), meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
    }

}