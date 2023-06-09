package com.mohamednader.healthyhabit.Favorites.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Favorites.Presenter.FavPresenter;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.OfflineMealsDetails.View.OfflineMealDetailsActivity;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment implements FavViewInterface, OnMealClickListener {

    public static final String EXTRA_MEAL_ID = "mealID";
    RecyclerView recyclerView;
    MealsAdapter favAdapter;
    FavPresenter favPresenter;
    List<Meal> resultItems;
    Meal meal;
    View view;
    TextView emptyMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fav, container, false);

        favPresenter = new FavPresenter(this,
                Repository.getInstance(getActivity(), ApiClient.getInstance(), ConcreteLocalSource.getInstance(getActivity())));
        initViews();
        recyclerViewConfig();

        favPresenter.getStoredMeals(Utils.getSp(getActivity())
                .getString(Utils.UserID, ""));

        return view;
    }

    private void initViews() {
        resultItems = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        emptyMsg = view.findViewById(R.id.empty_msg);
    }

    private void recyclerViewConfig() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favAdapter = new MealsAdapter(getActivity(), new ArrayList<>(), this, "Del");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(favAdapter);
    }

    @Override
    public void onMealClick(int mealID) {
        Intent intent = new Intent(getActivity(), OfflineMealDetailsActivity.class);
        intent.putExtra(EXTRA_MEAL_ID, mealID);
        startActivity(intent);
    }

    @Override
    public void showAllStoredMeals(LiveData<List<Meal>> mealsList) {
        mealsList.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> productList) {

                if (productList.size() == 0) {
                    emptyMsg.setVisibility(View.VISIBLE);
                } else {
                    emptyMsg.setVisibility(View.GONE);
                }

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
        // Handle fav meal click event here
    }

    @Override
    public void onDeletedFromFavSuccessfully() {
        Toast.makeText(getActivity(), meal.getStrMeal() + " Deleted !! ", Toast.LENGTH_SHORT).show();
    }
}
