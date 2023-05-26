package com.example.mycook.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycook.model.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance = null;

    public abstract MealDAO itemDAO();

    public static synchronized MealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class,
                            "mealdb")
                    .build();
        }
        return instance;
    }

}
