package com.quanglong.recipeapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingredient implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("unitOfMeasurement")
    public String quantity;

    public Ingredient() {
    }

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
