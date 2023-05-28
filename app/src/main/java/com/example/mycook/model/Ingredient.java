package com.example.mycook.model;

public class Ingredient {
    private String ingredient;
    private String measurement;

    public Ingredient(String ingredient, String measurement) {
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
