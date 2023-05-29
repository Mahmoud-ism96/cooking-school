package com.example.mycook.main.view.fragments.meal_search.view;

import com.example.mycook.model.Meal;

import java.util.List;

public interface MealSearchInterface {

    public void showSearchMeals(List<Meal> meal);

    public void addMeal(Meal meal);
}
