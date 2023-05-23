package com.example.mycook.main.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mycook.main.db.LocalSource;
import com.example.mycook.main.network.NetworkDelegate;
import com.example.mycook.main.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface {

    private Context context;
    RemoteSource remoteSource;
    LocalSource localSource;
    private static Repository repo = null;

    public static Repository getInstance(Context context, RemoteSource remoteSource, LocalSource localSource) {
        if (repo == null) {
            repo = new Repository(context, remoteSource, localSource);
        }
        return repo;
    }

    private Repository(Context context, RemoteSource remoteSource, LocalSource localSource) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    @Override
    public void getAllMeals(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return localSource.getAllStoredMeals();
    }

    @Override
    public void insertMeal(Meal meal) {
        localSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.deleteMeal(meal);
    }
}