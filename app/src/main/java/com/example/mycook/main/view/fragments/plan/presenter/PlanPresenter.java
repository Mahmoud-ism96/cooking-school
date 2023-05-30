package com.example.mycook.main.view.fragments.plan.presenter;

import com.example.mycook.main.view.fragments.plan.view.PlanInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.RepositoryInterface;

public class PlanPresenter implements PlanPresenterInterface {

    private PlanInterface view;
    private RepositoryInterface repo;

    private String TAG = "PlanPresenter";

    public PlanPresenter(PlanInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getDayMeals(String day) {
        view.showDayMeals(repo.getMealByPlanDay(day));
    }


    @Override
    public void removeFromWeek(Meal meal) {
        String mealId = Integer.toString(meal.getMealID());
        String noWeekDay = "";
        repo.updateMealPlanDay(mealId, noWeekDay);
    }
}
