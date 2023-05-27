package com.example.mycook.main.view.fragments.home.view;

import com.example.mycook.model.Meal;

import java.util.List;

public interface HomeInterface {

    public void showDailyInspiration(List<Meal> meal);

    public void addMeal(Meal meal);
}
