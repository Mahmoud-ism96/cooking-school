package com.example.mycook.main.view.fragments.meal_details.presenter;

import android.util.Log;

import com.example.mycook.main.view.fragments.meal_details.view.MealDetailsInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Meals;
import com.example.mycook.model.RepositoryInterface;
import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public class MealDetailsPresenter implements MealDetailsPresenterInterface, NetworkDelegate {

    private MealDetailsInterface view;
    private RepositoryInterface repo;

    private String TAG = "MealDetailsPresenter";

    public MealDetailsPresenter(MealDetailsInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getMealById(int id) {
        repo.getMealById(this, id);
    }

    @Override
    public void addToFav(Meal meal) {
        repo.insertMeal(meal);
    }

    @Override
    public void onSuccessResult(Meals meals) {
        List<Meal> meal = meals.getMeals();
        view.showRemoteMealDetails(meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "Failure: " + errorMsg);
    }
}
