package com.example.mycook.main.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "meals")
public class Meal {
    @PrimaryKey
    @NonNull
    @SerializedName("idMeal")
    @ColumnInfo(name = "mealID")
    private int mealID;

    @SerializedName("strMeal")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("strArea")
    @ColumnInfo(name = "area")
    private String area;

    @SerializedName("strCategory")
    @ColumnInfo(name = "category")
    private String category;

    @SerializedName("strInstructions")
    @ColumnInfo(name = "instructions")
    private String instructions;

    @SerializedName("strMealThumb")
    @ColumnInfo(name = "thumbnail")
    private String thumbnail;

    @SerializedName("strIngredient1")
    @ColumnInfo(name = "ingredient1")
    private String ingredient1;

    @SerializedName("strIngredient2")
    @ColumnInfo(name = "ingredient2")
    private String ingredient2;

    @SerializedName("strIngredient3")
    @ColumnInfo(name = "ingredient3")
    private String ingredient3;

    @SerializedName("strIngredient4")
    @ColumnInfo(name = "ingredient4")
    private String ingredient4;

    @SerializedName("strIngredient5")
    @ColumnInfo(name = "ingredient5")
    private String ingredient5;

    @SerializedName("strIngredient6")
    @ColumnInfo(name = "ingredient6")
    private String ingredient6;

    @SerializedName("strIngredient7")
    @ColumnInfo(name = "ingredient7")
    private String ingredient7;

    @SerializedName("strIngredient8")
    @ColumnInfo(name = "ingredient8")
    private String ingredient8;

    @SerializedName("strIngredient9")
    @ColumnInfo(name = "ingredient9")
    private String ingredient9;

    @SerializedName("strIngredient10")
    @ColumnInfo(name = "ingredient10")
    private String ingredient10;

    @SerializedName("strIngredient11")
    @ColumnInfo(name = "ingredient11")
    private String ingredient11;

    @SerializedName("strIngredient12")
    @ColumnInfo(name = "ingredient12")
    private String ingredient12;

    @SerializedName("strIngredient13")
    @ColumnInfo(name = "ingredient13")
    private String ingredient13;

    @SerializedName("strIngredient14")
    @ColumnInfo(name = "ingredient14")
    private String ingredient14;

    @SerializedName("strIngredient15")
    @ColumnInfo(name = "ingredient15")
    private String ingredient15;

    @SerializedName("strIngredient16")
    @ColumnInfo(name = "ingredient16")
    private String ingredient16;

    @SerializedName("strIngredient17")
    @ColumnInfo(name = "ingredient17")
    private String ingredient17;

    @SerializedName("strIngredient18")
    @ColumnInfo(name = "ingredient18")
    private String ingredient18;

    @SerializedName("strIngredient19")
    @ColumnInfo(name = "ingredient19")
    private String ingredient19;

    @SerializedName("strIngredient20")
    @ColumnInfo(name = "ingredient20")
    private String ingredient20;

    @SerializedName("strMeasure1")
    @ColumnInfo(name = "measurement1")
    private String measurement1;

    @SerializedName("strMeasure2")
    @ColumnInfo(name = "measurement2")
    private String measurement2;

    @SerializedName("strMeasure3")
    @ColumnInfo(name = "measurement3")
    private String measurement3;

    @SerializedName("strMeasure4")
    @ColumnInfo(name = "measurement4")
    private String measurement4;

    @SerializedName("strMeasure5")
    @ColumnInfo(name = "measurement5")
    private String measurement5;

    @SerializedName("strMeasure6")
    @ColumnInfo(name = "measurement6")
    private String measurement6;

    @SerializedName("strMeasure7")
    @ColumnInfo(name = "measurement7")
    private String measurement7;

    @SerializedName("strMeasure8")
    @ColumnInfo(name = "measurement8")
    private String measurement8;

    @SerializedName("strMeasure9")
    @ColumnInfo(name = "measurement9")
    private String measurement9;

    @SerializedName("strMeasure10")
    @ColumnInfo(name = "measurement10")
    private String measurement10;

    @SerializedName("strMeasure11")
    @ColumnInfo(name = "measurement11")
    private String measurement11;

    @SerializedName("strMeasure12")
    @ColumnInfo(name = "measurement12")
    private String measurement12;

    @SerializedName("strMeasure13")
    @ColumnInfo(name = "measurement13")
    private String measurement13;

    @SerializedName("strMeasure14")
    @ColumnInfo(name = "measurement14")
    private String measurement14;

    @SerializedName("strMeasure15")
    @ColumnInfo(name = "measurement15")
    private String measurement15;

    @SerializedName("strMeasure16")
    @ColumnInfo(name = "measurement16")
    private String measurement16;

    @SerializedName("strMeasure17")
    @ColumnInfo(name = "measurement17")
    private String measurement17;

    @SerializedName("strMeasure18")
    @ColumnInfo(name = "measurement18")
    private String measurement18;

    @SerializedName("strMeasure19")
    @ColumnInfo(name = "measurement19")
    private String measurement19;

    @SerializedName("strMeasure20")
    @ColumnInfo(name = "measurement20")
    private String measurement20;

    @SerializedName("strYoutube")
    @ColumnInfo(name = "youtubeURL")
    private String youtubeURL;

    public Meal(int mealID, String name, String area, String category, String instructions, String thumbnail, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11, String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16, String ingredient17, String ingredient18, String ingredient19, String ingredient20, String measurement1, String measurement2, String measurement3, String measurement4, String measurement5, String measurement6, String measurement7, String measurement8, String measurement9, String measurement10, String measurement11, String measurement12, String measurement13, String measurement14, String measurement15, String measurement16, String measurement17, String measurement18, String measurement19, String measurement20, String youtubeURL) {
        this.mealID = mealID;
        this.name = name;
        this.area = area;
        this.category = category;
        this.instructions = instructions;
        this.thumbnail = thumbnail;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
        this.ingredient11 = ingredient11;
        this.ingredient12 = ingredient12;
        this.ingredient13 = ingredient13;
        this.ingredient14 = ingredient14;
        this.ingredient15 = ingredient15;
        this.ingredient16 = ingredient16;
        this.ingredient17 = ingredient17;
        this.ingredient18 = ingredient18;
        this.ingredient19 = ingredient19;
        this.ingredient20 = ingredient20;
        this.measurement1 = measurement1;
        this.measurement2 = measurement2;
        this.measurement3 = measurement3;
        this.measurement4 = measurement4;
        this.measurement5 = measurement5;
        this.measurement6 = measurement6;
        this.measurement7 = measurement7;
        this.measurement8 = measurement8;
        this.measurement9 = measurement9;
        this.measurement10 = measurement10;
        this.measurement11 = measurement11;
        this.measurement12 = measurement12;
        this.measurement13 = measurement13;
        this.measurement14 = measurement14;
        this.measurement15 = measurement15;
        this.measurement16 = measurement16;
        this.measurement17 = measurement17;
        this.measurement18 = measurement18;
        this.measurement19 = measurement19;
        this.measurement20 = measurement20;
        this.youtubeURL = youtubeURL;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }

    public String getIngredient16() {
        return ingredient16;
    }

    public void setIngredient16(String ingredient16) {
        this.ingredient16 = ingredient16;
    }

    public String getIngredient17() {
        return ingredient17;
    }

    public void setIngredient17(String ingredient17) {
        this.ingredient17 = ingredient17;
    }

    public String getIngredient18() {
        return ingredient18;
    }

    public void setIngredient18(String ingredient18) {
        this.ingredient18 = ingredient18;
    }

    public String getIngredient19() {
        return ingredient19;
    }

    public void setIngredient19(String ingredient19) {
        this.ingredient19 = ingredient19;
    }

    public String getIngredient20() {
        return ingredient20;
    }

    public void setIngredient20(String ingredient20) {
        this.ingredient20 = ingredient20;
    }

    public String getMeasurement1() {
        return measurement1;
    }

    public void setMeasurement1(String measurement1) {
        this.measurement1 = measurement1;
    }

    public String getMeasurement2() {
        return measurement2;
    }

    public void setMeasurement2(String measurement2) {
        this.measurement2 = measurement2;
    }

    public String getMeasurement3() {
        return measurement3;
    }

    public void setMeasurement3(String measurement3) {
        this.measurement3 = measurement3;
    }

    public String getMeasurement4() {
        return measurement4;
    }

    public void setMeasurement4(String measurement4) {
        this.measurement4 = measurement4;
    }

    public String getMeasurement5() {
        return measurement5;
    }

    public void setMeasurement5(String measurement5) {
        this.measurement5 = measurement5;
    }

    public String getMeasurement6() {
        return measurement6;
    }

    public void setMeasurement6(String measurement6) {
        this.measurement6 = measurement6;
    }

    public String getMeasurement7() {
        return measurement7;
    }

    public void setMeasurement7(String measurement7) {
        this.measurement7 = measurement7;
    }

    public String getMeasurement8() {
        return measurement8;
    }

    public void setMeasurement8(String measurement8) {
        this.measurement8 = measurement8;
    }

    public String getMeasurement9() {
        return measurement9;
    }

    public void setMeasurement9(String measurement9) {
        this.measurement9 = measurement9;
    }

    public String getMeasurement10() {
        return measurement10;
    }

    public void setMeasurement10(String measurement10) {
        this.measurement10 = measurement10;
    }

    public String getMeasurement11() {
        return measurement11;
    }

    public void setMeasurement11(String measurement11) {
        this.measurement11 = measurement11;
    }

    public String getMeasurement12() {
        return measurement12;
    }

    public void setMeasurement12(String measurement12) {
        this.measurement12 = measurement12;
    }

    public String getMeasurement13() {
        return measurement13;
    }

    public void setMeasurement13(String measurement13) {
        this.measurement13 = measurement13;
    }

    public String getMeasurement14() {
        return measurement14;
    }

    public void setMeasurement14(String measurement14) {
        this.measurement14 = measurement14;
    }

    public String getMeasurement15() {
        return measurement15;
    }

    public void setMeasurement15(String measurement15) {
        this.measurement15 = measurement15;
    }

    public String getMeasurement16() {
        return measurement16;
    }

    public void setMeasurement16(String measurement16) {
        this.measurement16 = measurement16;
    }

    public String getMeasurement17() {
        return measurement17;
    }

    public void setMeasurement17(String measurement17) {
        this.measurement17 = measurement17;
    }

    public String getMeasurement18() {
        return measurement18;
    }

    public void setMeasurement18(String measurement18) {
        this.measurement18 = measurement18;
    }

    public String getMeasurement19() {
        return measurement19;
    }

    public void setMeasurement19(String measurement19) {
        this.measurement19 = measurement19;
    }

    public String getMeasurement20() {
        return measurement20;
    }

    public void setMeasurement20(String measurement20) {
        this.measurement20 = measurement20;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}