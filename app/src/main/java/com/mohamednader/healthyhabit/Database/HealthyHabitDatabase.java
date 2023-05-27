package com.mohamednader.healthyhabit.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class HealthyHabitDatabase extends RoomDatabase {

    private static HealthyHabitDatabase healthyHabitDatabase = null;
    public abstract HealthyHabitDAO healthyHabitDAO();
    public static synchronized HealthyHabitDatabase getInstance(Context context) {
        if (healthyHabitDatabase == null) {
            healthyHabitDatabase = Room.databaseBuilder(context.getApplicationContext(), HealthyHabitDatabase.class, "HealthyHabitDB")
                    .build();
        }
        return healthyHabitDatabase;
    }


}
