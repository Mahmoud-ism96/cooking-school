package com.example.mycook.account.presenter;

import com.example.mycook.model.RepositoryInterface;

public class AccountPresenter implements AccountPresenterInterface {

    private RepositoryInterface repo;


    private String TAG = "AccountPresenter";

    public AccountPresenter(RepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public void deleteAllMeals() {
        repo.deleteAllMeals();
    }
}
