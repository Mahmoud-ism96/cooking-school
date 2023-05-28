package com.example.mycook.network;

public interface RemoteSource {
    void dailyInspirationEnqueueCall(NetworkDelegate networkDelegate);

    void mealByIdEnqueueCall(NetworkDelegate networkDelegate, int id);

    void mealsByAreaEnqueueCall(NetworkDelegate networkDelegate, String area);

    void mealsByIngredientEnqueueCall(NetworkDelegate networkDelegate, String ingredient);

    void mealsByCategoryEnqueueCall(NetworkDelegate networkDelegate, String category);

    void getIngredientsEnqueueCall(NetworkDelegate networkDelegate);

    void getCategoriesEnqueueCall(NetworkDelegate networkDelegate);

    void getAreasEnqueueCall(NetworkDelegate networkDelegate);

}
