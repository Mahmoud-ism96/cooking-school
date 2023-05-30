package com.example.mycook.main.view.fragments.plan.view;

import androidx.lifecycle.LiveData;

import com.example.mycook.model.Meal;

import java.util.List;

public interface PlanInterface {
    public void showDayMeals(LiveData<List<Meal>> items);

    public void removeFromPlan(Meal meal);

}
