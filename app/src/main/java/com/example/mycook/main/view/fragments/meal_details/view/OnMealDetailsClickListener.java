package com.example.mycook.main.view.fragments.meal_details.view;

import com.example.mycook.model.Meal;

interface OnMealDetailsClickListener {

 void onFavClick(Meal meal);
 boolean mealExist(String mealID);
}
