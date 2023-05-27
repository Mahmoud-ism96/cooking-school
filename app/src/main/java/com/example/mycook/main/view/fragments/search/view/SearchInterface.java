package com.example.mycook.main.view.fragments.search.view;

import com.example.mycook.model.Meal;

import java.util.List;

public interface SearchInterface {
    public void showIngredients(List<Meal> meal);
    public void showCategories(List<Meal> meal);
    public void showAreas(List<Meal> meal);
}
