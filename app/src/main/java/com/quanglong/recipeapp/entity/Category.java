package com.quanglong.recipeapp.entity;

public class Category {
    public String name;
    public String recipe;
    public int imageRes;

    public Category(String name, String recipe, int imageRes) {
        this.name = name;
        this.recipe = recipe;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}