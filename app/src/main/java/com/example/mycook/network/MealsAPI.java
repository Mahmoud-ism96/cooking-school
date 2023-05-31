package com.example.mycook.network;

import android.content.Context;

import com.example.mycook.model.Meals;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsAPI implements RemoteSource {

    private static String TAG = "MEALS_API";
    private String BASE_URL = "https://themealdb.com/api/json/v1/1/";
    private static MealsAPI client = null;
    private static ApiInterface apiService;

    private MealsAPI(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        File cacheDirectory = new File(context.getCacheDir(), "offline_cache_directory");
        Cache cache = new Cache(cacheDirectory, 80 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder().cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(ApiInterface.class);
    }

    public static MealsAPI getInstance(Context context) {
        if (client == null) {
            client = new MealsAPI(context);
        }
        return client;
    }

    public static void dailyInspirationEnqueue(NetworkDelegate networkDelegate) {
        Call<Meals> call = apiService.getDailyInspiration();
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

    public static void mealsByIdEnqueue(NetworkDelegate networkDelegate, String id) {
        Call<Meals> call = apiService.getMealById(id);
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

    public static void mealsByNameEnqueue(NetworkDelegate networkDelegate, String mealName) {
        Call<Meals> call = apiService.getMealByName(mealName);
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

    public static void mealsByIngredientEnqueue(NetworkDelegate networkDelegate, String ingredient) {
        Call<Meals> call = apiService.getMealsByIngredient(ingredient);
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

    public static void mealsByCategoryEnqueue(NetworkDelegate networkDelegate, String category) {
        Call<Meals> call = apiService.getMealsByCategory(category);
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

    public static void mealsByAreaEnqueue(NetworkDelegate networkDelegate, String area) {
        Call<Meals> call = apiService.getMealsByArea(area);
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

    public static void getIngredientsEnqueue(NetworkDelegate networkDelegate) {
        Call<Meals> call = apiService.getIngredients();
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

    public static void getCategoriesEnqueue(NetworkDelegate networkDelegate) {
        Call<Meals> call = apiService.getCategories();
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

    public static void getAreasEnqueue(NetworkDelegate networkDelegate) {
        Call<Meals> call = apiService.getAreas();
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
    public void dailyInspirationEnqueueCall(NetworkDelegate networkDelegate) {
        dailyInspirationEnqueue(networkDelegate);
    }

    @Override
    public void mealByIdEnqueueCall(NetworkDelegate networkDelegate, String id) {
        mealsByIdEnqueue(networkDelegate, id);
    }

    @Override
    public void mealByNameEnqueueCall(NetworkDelegate networkDelegate, String mealName) {
        mealsByNameEnqueue(networkDelegate, mealName);
    }

    @Override
    public void mealsByIngredientEnqueueCall(NetworkDelegate networkDelegate, String ingredient) {
        mealsByIngredientEnqueue(networkDelegate, ingredient);
    }

    @Override
    public void mealsByCategoryEnqueueCall(NetworkDelegate networkDelegate, String category) {
        mealsByCategoryEnqueue(networkDelegate, category);
    }

    @Override
    public void mealsByAreaEnqueueCall(NetworkDelegate networkDelegate, String area) {
        mealsByAreaEnqueue(networkDelegate, area);
    }

    @Override
    public void getIngredientsEnqueueCall(NetworkDelegate networkDelegate) {
        getIngredientsEnqueue(networkDelegate);
    }

    @Override
    public void getCategoriesEnqueueCall(NetworkDelegate networkDelegate) {
        getCategoriesEnqueue(networkDelegate);
    }

    @Override
    public void getAreasEnqueueCall(NetworkDelegate networkDelegate) {
        getAreasEnqueue(networkDelegate);
    }
}
