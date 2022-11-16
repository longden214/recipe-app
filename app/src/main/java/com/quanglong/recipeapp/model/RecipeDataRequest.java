package com.quanglong.recipeapp.model;

import java.io.Serializable;
import java.util.List;

public class RecipeDataRequest implements Serializable {
    private Recipes recipe;
    private List<Step> listSteps;
    private List<Ingredient> listInfgredients;

    public RecipeDataRequest() {
    }

    public Recipes getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipes recipe) {
        this.recipe = recipe;
    }

    public List<Step> getListSteps() {
        return listSteps;
    }

    public void setListSteps(List<Step> listSteps) {
        this.listSteps = listSteps;
    }

    public List<Ingredient> getListInfgredients() {
        return listInfgredients;
    }

    public void setListInfgredients(List<Ingredient> listInfgredients) {
        this.listInfgredients = listInfgredients;
    }
}

class Steps{
    private String description;

    public Steps(String description) {
        this.description = description;
    }

    public Steps() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
