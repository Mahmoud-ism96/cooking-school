package com.example.mycook.main.view.fragments.meal_details.view;

import com.example.mycook.model.Meal;

import java.util.List;

public interface MealDetailsInterface {

 public void showRemoteMealDetails(List<Meal> meal);

 public void addMeal(Meal meal);
}
