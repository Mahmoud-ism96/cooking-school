package com.example.mycook.main.network;

import com.example.mycook.main.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/json/v1/1/random.php")
    Call<Meals> getDailyInspiration();

    @GET("api/json/v1/1/filter.php?a={area}")
    Call<Meals> getMealsByArea(@Path("area")String area);

    @GET("api/json/v1/1/filter.php?i={ingredient}")
    Call<Meals> getMealsByIngredient(@Path("ingredient")String ingredient);

    @GET("api/json/v1/1/filter.php?c={category}")
    Call<Meals> getMealsByCategory(@Path("category")String category);

    @GET("images/ingredients/{image}") //TODO: get the image from the API
    Call<Meals> getIngredientImage(@Path("image")String image);
}