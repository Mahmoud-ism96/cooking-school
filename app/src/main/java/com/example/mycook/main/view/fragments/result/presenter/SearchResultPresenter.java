package com.example.mycook.main.view.fragments.result.presenter;

import android.util.Log;

import com.example.mycook.main.view.fragments.result.view.SearchResultsInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Meals;
import com.example.mycook.model.RepositoryInterface;
import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public class SearchResultPresenter implements SearchResultPresenterInterface, NetworkDelegate {

    private SearchResultsInterface view;
    private RepositoryInterface repo;

    private String TAG = "SearchResultPresenter";

    public SearchResultPresenter(SearchResultsInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getSearchResultsByIngredients(String ingredient) {
        repo.getMealsByIngredient(this, ingredient);
    }

    @Override
    public void getSearchResultsByCategory(String category) {
        repo.getMealsByCategory(this, category);
    }

    @Override
    public void getSearchResultsByArea(String area) {
        repo.getMealsByArea(this, area);
    }

    @Override
    public void addToFav(Meal meal) {
        repo.insertMeal(meal);
    }

    @Override
    public void onSuccessResult(Meals meals) {
        List<Meal> meal = meals.getMeals();
        view.showSearchResults(meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "Failure: " + errorMsg);
    }
}
