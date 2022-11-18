package com.quanglong.recipeapp.model;

import java.io.Serializable;

public class Step implements Serializable {
    private int id;
    private int recipeId;
    private int stepNumber;
    private String description;
    private int status;

    public Step() {
    }

    public Step(int id, int recipeId, int stepNumber, String description, int status) {
        this.id = id;
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
