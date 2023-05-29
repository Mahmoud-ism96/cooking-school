package com.example.mycook.main.view.fragments.favourites.presenter;

import com.example.mycook.model.Meal;

public interface FavouritesPresenterInterface {

    public void getFavMeals();

    public void removeFromFav(Meal meal);

    public boolean mealExist(int mealId);
}
