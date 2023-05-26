package com.example.mycook.main.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mycook.main.model.Meal;

import java.util.List;

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
}