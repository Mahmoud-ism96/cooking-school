package com.example.mycook.main.view.fragments.result.presenter;

import com.example.mycook.model.Meal;

public interface SearchResultPresenterInterface {
    public void getSearchResultsByIngredients(String ingredient);

    public void getSearchResultsByCategory(String category);

    public void getSearchResultsByArea(String area);

    public void addToFav(Meal meal);
}
