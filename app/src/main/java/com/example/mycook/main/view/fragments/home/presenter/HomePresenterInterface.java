package com.example.mycook.main.view.fragments.home.presenter;

import com.example.mycook.model.Meal;

public interface HomePresenterInterface {
    public void getDailyInspiration();

    public void addToFav(Meal meal);
}
