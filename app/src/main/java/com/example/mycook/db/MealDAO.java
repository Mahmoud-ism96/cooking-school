package com.example.mycook.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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

    @Query("SELECT EXISTS(SELECT * FROM meals WHERE mealID = :id)")
    boolean hasMeal(String id);

    @Query("SELECT * From meals WHERE weekday = :day")
    LiveData<List<Meal>> getMealByPlanDay(String day);

    @Query("UPDATE meals SET weekday = :day WHERE mealID = :meal_id")
    void updateMealPlanDay(String meal_id, String day);

    @Query("UPDATE meals set weekday = null WHERE mealID = :meal_id")
    void deletePlanDay(String meal_id);

    @Query("DELETE FROM meals")
    void deleteAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllMeal (List<Meal> meal);
}
