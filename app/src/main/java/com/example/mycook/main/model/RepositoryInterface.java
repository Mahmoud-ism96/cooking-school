package com.example.mycook.main.model;

import androidx.lifecycle.LiveData;

import com.example.mycook.main.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
 public void getAllMeals(NetworkDelegate networkDelegate);

 public void insertMeal(Meal meal);

 public LiveData<List<Meal>> getStoredMeals();

 public void deleteMeal(Meal meal);
}
