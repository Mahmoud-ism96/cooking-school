package com.example.mycook.main.presenter;

import com.example.mycook.model.Meal;

import java.util.List;

public interface MainPresenterInterface {
    public void insertAllMeals(List<Meal> meals);

    public void getStoredMeals();

}
