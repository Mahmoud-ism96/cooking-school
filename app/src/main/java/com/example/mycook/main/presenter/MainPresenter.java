package com.example.mycook.main.presenter;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.mycook.main.view.MainInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.RepositoryInterface;

import java.util.List;

public class MainPresenter implements MainPresenterInterface {

    private MainInterface view;
    private RepositoryInterface repo;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private String TAG = "MainPresenter";

    public MainPresenter(MainInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void insertAllMeals(List<Meal> meals) {
        repo.insertAllMeals(meals);
    }

    @Override
    public void getStoredMeals() {
        repo.getStoredMeals().observe((LifecycleOwner) view, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                view.getAllStoredMeals(meals);
            }
        });
    }
}