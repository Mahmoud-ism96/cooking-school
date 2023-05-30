package com.example.mycook.main.view.fragments.home.view;

import com.example.mycook.model.Meal;

public interface OnDailyMealClickListener {
    void onFavClick(Meal meal);
    void onMealClick(Meal meal);
    boolean mealExist(String mealID);
}
