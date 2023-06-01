package com.mohamednader.healthyhabit.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mohamednader.healthyhabit.Models.MealsModels.Meal;

@Database(entities = {Meal.class}, version = 3)
public abstract class HealthyHabitDatabase extends RoomDatabase {

    private static HealthyHabitDatabase healthyHabitDatabase = null;

    public static synchronized HealthyHabitDatabase getInstance(Context context) {
        if (healthyHabitDatabase == null) {
            healthyHabitDatabase = Room.databaseBuilder(context.getApplicationContext(), HealthyHabitDatabase.class, "HealthyHabitDB")
                    .build();
        }
        return healthyHabitDatabase;
    }

    public abstract HealthyHabitDAO healthyHabitDAO();


}
