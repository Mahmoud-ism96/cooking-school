package com.example.mycook.model;

import androidx.lifecycle.LiveData;

import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
    public void getDailyInspiration(NetworkDelegate networkDelegate);

    public void getMealById(NetworkDelegate networkDelegate, int id);

    public void getMealByName(NetworkDelegate networkDelegate, String mealName);

    public void getMealsByIngredient(NetworkDelegate networkDelegate, String ingredient);

    public void getMealsByCategory(NetworkDelegate networkDelegate, String category);

    public void getMealsByArea(NetworkDelegate networkDelegate, String area);

    public void getIngredients(NetworkDelegate networkDelegate);

    public void getCategories(NetworkDelegate networkDelegate);

    public void getAreas(NetworkDelegate networkDelegate);

    public void insertMeal(Meal meal);

    public LiveData<List<Meal>> getStoredMeals();

    public LiveData<List<Meal>> getMealByPlanDay(String day);

    public void updateMealPlanDay(String meal_id, String day);

    public void deletePlanDay(String meal_id);

    public void deleteMeal(Meal meal);

    public boolean hasMeal(int id);
}
