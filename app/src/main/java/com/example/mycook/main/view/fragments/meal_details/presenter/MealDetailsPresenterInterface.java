package com.example.mycook.main.view.fragments.meal_details.presenter;

import com.example.mycook.model.Meal;

public interface MealDetailsPresenterInterface {
    public void getMealById(int id);

    public boolean mealExist(int mealId);

    public void addToFav(Meal meal);
}
