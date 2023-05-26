package com.example.mycook.main.model;

import androidx.lifecycle.LiveData;

import com.example.mycook.main.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
    public void getDailyInspiration(NetworkDelegate networkDelegate);

    public void getMealsByArea(NetworkDelegate networkDelegate, String area);

    public void getMealsByIngredient(NetworkDelegate networkDelegate, String ingredient);

    public void getMealsByCategory(NetworkDelegate networkDelegate, String category);

    public void insertMeal(Meal meal);

    public LiveData<List<Meal>> getStoredMeals();

    public void deleteMeal(Meal meal);
}
