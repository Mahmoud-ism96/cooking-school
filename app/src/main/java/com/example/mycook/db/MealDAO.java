package com.example.mycook.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mycook.model.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * from meals")
    LiveData<List<Meal>> getAllMeals();

    @Insert
    void insertMeal(Meal item);

    @Delete
    void deleteMeal(Meal item);
}
