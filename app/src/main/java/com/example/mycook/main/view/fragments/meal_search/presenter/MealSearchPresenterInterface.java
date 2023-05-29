package com.example.mycook.main.view.fragments.meal_search.presenter;

import com.example.mycook.model.Meal;

public interface MealSearchPresenterInterface {
    public void getMeals(String mealName);

    public boolean mealExist(int mealId);

    public void addToFav(Meal meal);
}
