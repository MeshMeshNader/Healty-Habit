package com.mohamednader.healthyhabit.Category.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Category.Presenter.CategoryPresenter;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements CategoryViewInterface, OnMealClickListener {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageCategory, imageCategoryBg;
    TextView textCategory;
    MealsAdapter mealsAdapter;
    CategoryPresenter categoryPresenter;
    AlertDialog.Builder descDialog;

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

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        imageCategory = view.findViewById(R.id.imageCategory);
        imageCategoryBg = view.findViewById(R.id.imageCategoryBg);
        textCategory = view.findViewById(R.id.textCategory);

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
                    Repository.getInstance(getActivity(), ApiClient.getInstance()));

            categoryPresenter.getMealsByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }



    @Override
    public void showMealsByCategory(List<Meal> list) {

        mealsAdapter.setList(list);
        mealsAdapter.notifyDataSetChanged();

        /*mealsAdapter.setOnItemClickListener((view, position) -> {
            //TODO #8.2 make an intent to DetailActivity (get the name of the meal from the edit text view, then send the name of the meal to DetailActivity)
        });*/
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);

    }

//    @Override
//    public void onErrorLoading(String message) {
//       // Utils.showDialogMessage(getActivity(), "Error ", message);
//    }

    @Override
    public void onMealClick(int mealID) {
        Toast.makeText(getActivity(), "You Clicked " + mealID, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFavMealClick(Meal meal) {
        Toast.makeText(getActivity(), "You Clicked Fav " + meal.getStrMeal(), Toast.LENGTH_SHORT).show();

    }
}