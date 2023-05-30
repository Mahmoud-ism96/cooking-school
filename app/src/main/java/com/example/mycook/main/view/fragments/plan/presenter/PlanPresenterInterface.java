package com.example.mycook.main.view.fragments.plan.presenter;

import com.example.mycook.model.Meal;

public interface PlanPresenterInterface {
    public void getDayMeals(String day);

    public void removeFromWeek(Meal meal);

}
