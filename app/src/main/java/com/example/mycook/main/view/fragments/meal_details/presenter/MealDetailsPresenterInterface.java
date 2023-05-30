package com.example.mycook.main.view.fragments.meal_details.presenter;

import com.example.mycook.model.Meal;

public interface MealDetailsPresenterInterface {
    public void getMealById(String id);

    public boolean mealExist(String mealId);

    public void addToFav(Meal meal);

    public void addToPlan(Meal meal, String day);
}
