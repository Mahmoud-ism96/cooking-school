package com.example.mycook.main.network;

import com.example.mycook.main.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("meals")
    Call<Meals> getProducts();
}