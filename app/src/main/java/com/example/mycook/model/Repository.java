package com.example.mycook.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mycook.db.LocalSource;
import com.example.mycook.network.NetworkDelegate;
import com.example.mycook.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface {

    String TAG = "REPOSITORY";
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
    public void getDailyInspiration(NetworkDelegate networkDelegate) {
        remoteSource.dailyInspirationEnqueueCall(networkDelegate);
    }

    @Override
    public void getMealById(NetworkDelegate networkDelegate, String id) {
        remoteSource.mealByIdEnqueueCall(networkDelegate, id);
    }

    @Override
    public void getMealByName(NetworkDelegate networkDelegate, String mealName) {
        remoteSource.mealByNameEnqueueCall(networkDelegate, mealName);
    }

    @Override
    public void getMealsByArea(NetworkDelegate networkDelegate, String area) {
        remoteSource.mealsByAreaEnqueueCall(networkDelegate, area);
    }

    @Override
    public void getMealsByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        remoteSource.mealsByIngredientEnqueueCall(networkDelegate, ingredient);
    }

    @Override
    public void getMealsByCategory(NetworkDelegate networkDelegate, String category) {
        remoteSource.mealsByCategoryEnqueueCall(networkDelegate, category);
    }

    @Override
    public void getIngredients(NetworkDelegate networkDelegate) {
        remoteSource.getIngredientsEnqueueCall(networkDelegate);
    }

    @Override
    public void getCategories(NetworkDelegate networkDelegate) {
        remoteSource.getCategoriesEnqueueCall(networkDelegate);
    }

    @Override
    public void getAreas(NetworkDelegate networkDelegate) {
        remoteSource.getAreasEnqueueCall(networkDelegate);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return localSource.getAllStoredMeals();
    }

    @Override
    public LiveData<List<Meal>> getMealByPlanDay(String day) {
        return localSource.getMealByPlanDay(day);
    }

    @Override
    public void updateMealPlanDay(String meal_id, String day) {
        localSource.updateMealPlanDay(meal_id, day);
    }

    @Override
    public void deletePlanDay(String meal_id) {
        localSource.deletePlanDay(meal_id);
    }

    @Override
    public void insertMeal(Meal meal) {
        localSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.deleteMeal(meal);
    }

    @Override
    public boolean hasMeal(String id) {
        return localSource.hasMeal(id);
    }

    @Override
    public void deleteAllMeals() {
        localSource.deleteAllMeals();
    }

    @Override
    public void insertAllMeals(List<Meal> meal) {
        localSource.insertAllMeal(meal);
    }
}
