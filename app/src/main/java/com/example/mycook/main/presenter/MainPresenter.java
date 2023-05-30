package com.example.mycook.main.presenter;

import com.example.mycook.model.Meal;
import com.example.mycook.model.RepositoryInterface;

import java.util.List;

public class MainPresenter implements MainPresenterInterface {

    private RepositoryInterface repo;

    private String TAG = "MainPresenter";

    public MainPresenter(RepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public void insertAllMeals(List<Meal> meals) {
        repo.insertAllMeals(meals);
    }
}