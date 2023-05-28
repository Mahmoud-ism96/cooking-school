package com.example.mycook.main.view.fragments.ingredients.presenter;

import android.util.Log;

import com.example.mycook.main.view.fragments.ingredients.view.IngredientInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Meals;
import com.example.mycook.model.RepositoryInterface;
import com.example.mycook.network.NetworkDelegate;

import java.util.List;

public class IngredientsPresenter implements IngredientsPresenterInterface, NetworkDelegate {

    private IngredientInterface view;
    private RepositoryInterface repo;

    private String TAG = "Ingredients_Presenter";

    public IngredientsPresenter(IngredientInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getIngredients() {
        repo.getIngredients(this);
    }

    @Override
    public void onSuccessResult(Meals meals) {
        List<Meal> items = meals.getMeals();
        view.showIngredients(items);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "Failure: " + errorMsg);
    }
}
