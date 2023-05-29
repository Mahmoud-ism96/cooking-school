package com.example.mycook.main.view.fragments.favourites.presenter;

import com.example.mycook.main.view.fragments.favourites.view.FavouritesInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.RepositoryInterface;

public class FavouritesPresenter implements FavouritesPresenterInterface {

    private FavouritesInterface view;
    private RepositoryInterface repo;

    private String TAG = "FavouritesPresenter";

    public FavouritesPresenter(FavouritesInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void getFavMeals() {
        view.showFavMeals(repo.getStoredMeals());
    }

    @Override
    public boolean mealExist(int mealId) {
        return repo.hasMeal(mealId);
    }

    @Override
    public void removeFromFav(Meal meal) {
        repo.deleteMeal(meal);
    }
}
