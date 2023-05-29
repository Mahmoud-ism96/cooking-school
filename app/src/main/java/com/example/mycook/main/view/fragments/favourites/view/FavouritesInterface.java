package com.example.mycook.main.view.fragments.favourites.view;

import androidx.lifecycle.LiveData;

import com.example.mycook.model.Meal;

import java.util.List;

public interface FavouritesInterface {

    public void showFavMeals(LiveData<List<Meal>> items);

    public void deleteMeal(Meal meal);
}
