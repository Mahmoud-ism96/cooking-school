package com.example.mycook.account.presenter;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.mycook.account.view.AccountInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.RepositoryInterface;

import java.util.List;

public class AccountPresenter implements AccountPresenterInterface {

    private AccountInterface view;
    private RepositoryInterface repo;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private String TAG = "Home_Presenter";

    public AccountPresenter(AccountInterface view, RepositoryInterface repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void getStoredMeals() {

        repo.getStoredMeals().observe((LifecycleOwner) view, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.getAllStoredMeals(meals);
                    }
                });
            }
        });
    }

    @Override
    public void deleteAllMeals() {
        repo.deleteAllMeals();
    }
}
