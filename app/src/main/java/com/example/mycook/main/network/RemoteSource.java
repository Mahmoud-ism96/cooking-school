package com.example.mycook.main.network;

public interface RemoteSource {
    void dailyInspirationEnqueueCall(NetworkDelegate networkDelegate);

    void mealsByAreaEnqueueCall(NetworkDelegate networkDelegate, String area);

    void mealsByIngredientEnqueueCall(NetworkDelegate networkDelegate, String ingredient);

    void mealsByCategoryEnqueueCall(NetworkDelegate networkDelegate, String category);

}
