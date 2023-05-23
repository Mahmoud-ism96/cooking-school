package com.example.mycook.main.db;

import androidx.lifecycle.LiveData;

import com.example.mycook.main.model.Meal;

import java.util.List;

public interface LocalSource {

    public void insertMeal(Meal item);

    public void deleteMeal(Meal item);

    public LiveData<List<Meal>> getAllStoredMeals();
}
