package com.example.mycook.main.network;

import com.example.mycook.main.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsAPI implements RemoteSource {
    public static String BASE_URL = "https://themealdb.com/";
    private static Retrofit retrofit;
    private static MealsAPI client = null;

    private MealsAPI() {
    }

    public static MealsAPI getInstance() {
        if (client == null) {
            client = new MealsAPI();
        }
        return client;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static void enqueue(NetworkDelegate networkDelegate) {
        ApiInterface apiService = MealsAPI.getClient().create(ApiInterface.class);
        Call<Meals> call = apiService.getMeals();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful()) {
                    networkDelegate.onSuccessResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                networkDelegate.onFailureResult(t.getMessage());
            }
        });
    }

    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        enqueue(networkDelegate);
    }
}
