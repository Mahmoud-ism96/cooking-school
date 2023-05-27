package com.example.mycook.main.view.fragments.search.presenter;

import android.util.Log;

import com.example.mycook.main.view.fragments.search.view.SearchInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Meals;
import com.example.mycook.model.RepositoryInterface;
import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, NetworkDelegate {

    private SearchInterface view;
    private RepositoryInterface repo;

    private String TAG = "Search_Presenter";

    public SearchPresenter(SearchInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getIngredients() {
        repo.getIngredients(this);
    }

    @Override
    public void getCategories() {
        repo.getCategories(this);
    }

    @Override
    public void getAreas() {
        repo.getAreas(this);
    }

    @Override
    public void onSuccessResult(Meals meals) {
        List<Meal> items = meals.getMeals();
        if (!items.get(0).getIngredient().equals(""))
            view.showIngredients(items);
        else if(!items.get(0).getCategory().equals(""))
            view.showCategories(items);
        else if(!items.get(0).getArea().equals("")) {
            Log.i(TAG,items.get(0).getArea());
            view.showAreas(items);
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "Failure: " + errorMsg);
    }
}
