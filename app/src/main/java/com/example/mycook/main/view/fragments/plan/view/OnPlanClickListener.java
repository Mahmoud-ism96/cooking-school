package com.example.mycook.main.view.fragments.plan.view;

import com.example.mycook.model.Meal;

public interface OnPlanClickListener {
    public void selectDayMeals(String day);

    public void onMealClick(Meal meal);

    public void removeFromPlan(Meal meal);
}
