package com.example.mycook.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mycook.model.Meal;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ConcreteLocalSource implements LocalSource {

    private MealDAO dao;
    private static ConcreteLocalSource localSource = null;
    private LiveData<List<Meal>> storedMeals;

    private ConcreteLocalSource(Context context) {
        MealDatabase db = MealDatabase.getInstance(context.getApplicationContext());
        dao = db.itemDAO();
        storedMeals = dao.getAllMeals();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getAllStoredMeals() {
        return storedMeals;
    }

    @Override
    public boolean hasMeal(int id) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return dao.hasMeal(id);
            }
        };
        FutureTask<Boolean> task = new FutureTask<>(callable);
        new Thread(task).start();
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

}