package com.example.mycook.db;

import androidx.lifecycle.LiveData;

import com.example.mycook.model.Meal;

import java.util.List;

public interface LocalSource {

    public void insertMeal(Meal item);

    public void deleteMeal(Meal item);

    public LiveData<List<Meal>> getAllStoredMeals();
}
