package com.example.mycook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserData {
    String email;
    List<Meal> storedMeals;

    public UserData(String email, List<Meal> storedMeals) {
        this.email = email;
        this.storedMeals = storedMeals;
    }

    public UserData(Map<String, Object> data) {
        this.email = (String) data.get("email");

        // Convert the favMeals field from the Map to a List<Meal>
        List<Map<String, Object>> storedMealsData = (List<Map<String, Object>>) data.get("storedMeals");
        if (storedMealsData != null) {
            this.storedMeals = new ArrayList<>();
            for (Map<String, Object> mealData : storedMealsData) {
                Meal meal = new Meal();
                meal.setMealID((String) mealData.get("mealID"));
                meal.setWeekDay((String) mealData.get("weekDay"));
                meal.setName((String) mealData.get("name"));
                meal.setCategory((String) mealData.get("category"));
                meal.setArea((String) mealData.get("area"));
                meal.setInstructions((String) mealData.get("instructions"));
                meal.setThumbnail((String) mealData.get("thumbnail"));
                meal.setYoutubeURL((String) mealData.get("youtubeURL"));
                meal.setIngredient1((String) mealData.get("ingredient1"));
                meal.setIngredient2((String) mealData.get("ingredient2"));
                meal.setIngredient3((String) mealData.get("ingredient3"));
                meal.setIngredient4((String) mealData.get("ingredient4"));
                meal.setIngredient5((String) mealData.get("ingredient5"));
                meal.setIngredient6((String) mealData.get("ingredient6"));
                meal.setIngredient7((String) mealData.get("ingredient7"));
                meal.setIngredient8((String) mealData.get("ingredient8"));
                meal.setIngredient9((String) mealData.get("ingredient9"));
                meal.setIngredient10((String) mealData.get("ingredient10"));
                meal.setIngredient11((String) mealData.get("ingredient11"));
                meal.setIngredient12((String) mealData.get("ingredient12"));
                meal.setIngredient13((String) mealData.get("ingredient13"));
                meal.setIngredient14((String) mealData.get("ingredient14"));
                meal.setIngredient15((String) mealData.get("ingredient15"));
                meal.setIngredient16((String) mealData.get("ingredient16"));
                meal.setIngredient17((String) mealData.get("ingredient17"));
                meal.setIngredient18((String) mealData.get("ingredient18"));
                meal.setIngredient19((String) mealData.get("ingredient19"));
                meal.setIngredient20((String) mealData.get("ingredient20"));
                meal.setMeasurement1((String) mealData.get("measurement1"));
                meal.setMeasurement2((String) mealData.get("measurement2"));
                meal.setMeasurement3((String) mealData.get("measurement3"));
                meal.setMeasurement4((String) mealData.get("measurement4"));
                meal.setMeasurement5((String) mealData.get("measurement5"));
                meal.setMeasurement6((String) mealData.get("measurement6"));
                meal.setMeasurement7((String) mealData.get("measurement7"));
                meal.setMeasurement8((String) mealData.get("measurement8"));
                meal.setMeasurement9((String) mealData.get("measurement9"));
                meal.setMeasurement10((String) mealData.get("measurement10"));
                meal.setMeasurement11((String) mealData.get("measurement11"));
                meal.setMeasurement12((String) mealData.get("measurement12"));
                meal.setMeasurement13((String) mealData.get("measurement13"));
                meal.setMeasurement14((String) mealData.get("measurement14"));
                meal.setMeasurement15((String) mealData.get("measurement15"));
                meal.setMeasurement16((String) mealData.get("measurement16"));
                meal.setMeasurement17((String) mealData.get("measurement17"));
                meal.setMeasurement18((String) mealData.get("measurement18"));
                meal.setMeasurement19((String) mealData.get("measurement19"));
                meal.setMeasurement20((String) mealData.get("measurement20"));
                this.storedMeals.add(meal);
            }
        }
    }

    public UserData(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Meal> getStoredMeals() {
        return storedMeals;
    }

    public void setStoredMeals(List<Meal> storedMeals) {
        this.storedMeals = storedMeals;
    }
}
