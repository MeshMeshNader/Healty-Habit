package com.mohamednader.healthyhabit.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource {

    private Context context;
    private HealthyHabitDAO healthyHabitDAO;
    private LiveData<List<Meal>> storedMeals;
    private static ConcreteLocalSource localSource = null;


    private ConcreteLocalSource(Context context) {
        this.context = context;
        HealthyHabitDatabase db = HealthyHabitDatabase.getInstance(context.getApplicationContext());
        healthyHabitDAO = db.healthyHabitDAO();
        storedMeals = healthyHabitDAO.getAllMeals();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }


    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                healthyHabitDAO.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                healthyHabitDAO.deleteMeal(meal);
            }
        }).start();
    }
}
