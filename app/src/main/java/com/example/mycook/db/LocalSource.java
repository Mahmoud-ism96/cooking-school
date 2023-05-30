package com.example.mycook.db;

import androidx.lifecycle.LiveData;

import com.example.mycook.model.Meal;

import java.util.List;

public interface LocalSource {

    public void insertMeal(Meal item);

    public void deleteMeal(Meal item);

    public LiveData<List<Meal>> getAllStoredMeals();

    public LiveData<List<Meal>> getMealByPlanDay(String day);

    public void updateMealPlanDay(String meal_id, String day);

    public void deletePlanDay(String meal_id);

    public boolean hasMeal(String id);

    public void deleteAllMeals();

    public void insertAllMeal(List<Meal> meal);
}
