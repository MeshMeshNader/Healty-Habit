package com.mohamednader.healthyhabit.Area.View;

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
import com.mohamednader.healthyhabit.Area.Presenter.AreaPresenter;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.MealDetails.View.MealDetailsActivity;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.R;

import java.util.ArrayList;
import java.util.List;


public class AreaFragment extends Fragment implements AreaViewInterface, OnMealClickListener {


    public static final String EXTRA_MEAL_ID = "mealID";
    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageArea, imageAreaBg;
    TextView textArea;
    MealsAdapter mealsAdapter;
    AreaPresenter areaPresenter;
    CardView areaCardView;
    Meal meal;


    public AreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_area, container, false);

        initViews();
        recyclerViewConfig();
        return view;
    }


    private void initViews() {

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        imageArea = view.findViewById(R.id.imageArea);
        imageAreaBg = view.findViewById(R.id.imageAreaBg);
        textArea = view.findViewById(R.id.textArea);
        areaCardView = view.findViewById(R.id.cardArea);

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
            textArea.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Glide.with(getActivity())
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageArea);
            Glide.with(getActivity())
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .into(imageAreaBg);

            areaPresenter = new AreaPresenter(this,
                    Repository.getInstance(getActivity(), ApiClient.getInstance(), ConcreteLocalSource.getInstance(getActivity())));

            areaPresenter.getMealsByArea(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }


    @Override
    public void showMealsByArea(List<Meal> list) {

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

//    @Override
//    public void onErrorLoading(String message) {
//       // Utils.showDialogMessage(getActivity(), "Error ", message);
//    }

    @Override
    public void onMealClick(int mealID) {
        //Toast.makeText(getActivity(), "You Clicked " + mealID, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);

    }

    @Override
    public void onDeleteMealClick(Meal meal) {

    }

    @Override
    public void onFavMealClick(Meal meal) {
        this.meal = meal;
        areaPresenter.addMealToFav(meal);
    }

    @Override
    public void onAddedToFavSuccessfully() {
        Toast.makeText(getActivity(), meal.getStrMeal() + " Added To Fav! ", Toast.LENGTH_SHORT).show();
    }

}