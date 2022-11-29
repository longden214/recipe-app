package com.quanglong.recipeapp.model;

public class SaveRecipeRequest {
    public SaveRecipeRequest() {
    }

    private int recipeId;
    private int userId;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SaveRecipeRequest(int recipeId, int userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    }
}
