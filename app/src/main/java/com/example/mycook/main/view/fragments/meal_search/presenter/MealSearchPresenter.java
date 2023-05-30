package com.example.mycook.main.view.fragments.meal_search.presenter;

import android.util.Log;

import com.example.mycook.main.view.fragments.meal_search.view.MealSearchInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Meals;
import com.example.mycook.model.RepositoryInterface;
import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public class MealSearchPresenter implements MealSearchPresenterInterface, NetworkDelegate {

    private MealSearchInterface view;
    private RepositoryInterface repo;

    private String TAG = "Search_Presenter";

    public MealSearchPresenter(MealSearchInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getMeals(String mealName) {
        repo.getMealByName(this, mealName);
    }

    @Override
    public boolean mealExist(String mealId) {
        return repo.hasMeal(mealId);
    }

    @Override
    public void addToFav(Meal meal) {
        if (mealExist(meal.getMealID())) {
            repo.deleteMeal(meal);
        } else {
            repo.insertMeal(meal);
        }
    }

    @Override
    public void onSuccessResult(Meals meals) {
        List<Meal> items = meals.getMeals();
        view.showSearchMeals(items);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "Failure: " + errorMsg);
    }
}
