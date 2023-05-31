package com.mohamednader.healthyhabit.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

import java.util.List;

@Dao
public interface HealthyHabitDAO {

    @Query("SELECT * FROM meals")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

}
