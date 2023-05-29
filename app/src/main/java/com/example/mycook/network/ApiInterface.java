package com.example.mycook.network;

import com.example.mycook.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("random.php")
    Call<Meals> getDailyInspiration();

    @GET("lookup.php")
    Call<Meals> getMealById(@Query("i") int id);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);

    @GET("filter.php")
    Call<Meals> getMealsByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<Meals> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<Meals> getMealsByArea(@Query("a") String area);

    @GET("list.php?i=list")
    Call<Meals> getIngredients();

    @GET("list.php?c=list")
    Call<Meals> getCategories();

    @GET("list.php?a=list")
    Call<Meals> getAreas();

}