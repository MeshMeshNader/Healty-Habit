package com.mohamednader.healthyhabit.Planning.View;

import static com.mohamednader.healthyhabit.Planning.Presenter.CalendarUtils.daysInWeekArray;
import static com.mohamednader.healthyhabit.Planning.Presenter.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamednader.healthyhabit.Adapters.MealsAdapter;
import com.mohamednader.healthyhabit.Adapters.OnMealClickListener;
import com.mohamednader.healthyhabit.Database.ConcreteLocalSource;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.Models.Repository;
import com.mohamednader.healthyhabit.Network.ApiClient;
import com.mohamednader.healthyhabit.OfflineMealsDetails.View.OfflineMealDetailsActivity;
import com.mohamednader.healthyhabit.Planning.Presenter.CalendarAdapter;
import com.mohamednader.healthyhabit.Planning.Presenter.CalendarUtils;
import com.mohamednader.healthyhabit.Planning.Presenter.WeekPresenter;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Search.View.SearchActivity;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeekActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, OnMealClickListener, WeekViewInterface {
    public static final String EXTRA_MEAL_ID = "mealID";
    RecyclerView recyclerView;
    MealsAdapter mealAdapter;
    WeekPresenter weekPresenter;
    List<Meal> resultItems;
    Meal meal;
    TextView emptyMsg;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        weekPresenter = new WeekPresenter(this,
                Repository.getInstance(this, ApiClient.getInstance(), ConcreteLocalSource.getInstance(this)));

        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();

        weekPresenter.getStoredMeals(Utils.getSp(this)
                .getString(Utils.UserID, ""), CalendarUtils.selectedDate.toString());

    }

    private void initWidgets() {
        emptyMsg = findViewById(R.id.empty_msg);
        recyclerView = findViewById(R.id.recyclerView);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mealAdapter = new MealsAdapter(this, new ArrayList<>(), this, "Del");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(mealAdapter);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        weekPresenter.getStoredMeals(Utils.getSp(this)
                .getString(Utils.UserID, ""), CalendarUtils.selectedDate.toString());
    }


    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    public void newEventAction(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("PLAN", true);
        startActivity(intent);
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

                if (productList.size() == 0) {
                    emptyMsg.setVisibility(View.VISIBLE);
                } else {
                    emptyMsg.setVisibility(View.GONE);
                }

                mealAdapter.setList(productList);
                mealAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDeleteMealClick(Meal meal) {
        this.meal = meal;
        weekPresenter.deleteMealFromFav(meal);
    }

    @Override
    public void onFavMealClick(Meal meal) {
        // Handle fav meal click event here
    }

    @Override
    public void onDeletedFromFavSuccessfully() {
        weekPresenter.getStoredMeals(Utils.getSp(this)
                .getString(Utils.UserID, ""), CalendarUtils.selectedDate.toString());
        Toast.makeText(this, meal.getStrMeal() + " Deleted !! ", Toast.LENGTH_SHORT).show();
    }
}