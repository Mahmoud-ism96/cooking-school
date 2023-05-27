package com.example.mycook.network;

import com.example.mycook.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("random.php")
    Call<Meals> getDailyInspiration();

    @GET("filter.php?i={ingredient}")
    Call<Meals> getMealsByIngredient(@Path("ingredient") String ingredient);

    @GET("filter.php?c={category}")
    Call<Meals> getMealsByCategory(@Path("category") String category);

    @GET("filter.php?a={area}")
    Call<Meals> getMealsByArea(@Path("area") String area);

    @GET("list.php?i=list")
    Call<Meals> getIngredients();

    @GET("list.php?c=list")
    Call<Meals> getCategories();

    @GET("list.php?a=list")
    Call<Meals> getAreas();

}