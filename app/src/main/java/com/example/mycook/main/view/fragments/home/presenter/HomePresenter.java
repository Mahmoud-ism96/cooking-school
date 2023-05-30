package com.example.mycook.main.view.fragments.home.presenter;

import android.util.Log;

import com.example.mycook.main.view.fragments.home.view.HomeInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Meals;
import com.example.mycook.model.RepositoryInterface;
import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkDelegate {

    private HomeInterface view;
    private RepositoryInterface repo;

    private String TAG = "Home_Presenter";

    public HomePresenter(HomeInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getDailyInspiration() {
        repo.getDailyInspiration(this);
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
        view.showDailyInspiration(items);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "Failure: " + errorMsg);
    }
}
