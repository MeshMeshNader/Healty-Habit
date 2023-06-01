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

    @Query("SELECT * FROM meals WHERE idMeal = :mealId")
    LiveData<List<Meal>> getAllMealDetails(int mealId);

    @Query("SELECT * FROM meals WHERE strIngredient20 = :userID")
    LiveData<List<Meal>> getAllMealsFav(String userID);

    @Query("SELECT * FROM meals WHERE strIngredient20 = :userID AND strMeasure20 = :date")
    LiveData<List<Meal>> getAllMealsPlan(String userID, String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

}
