package com.quanglong.recipeapp.model;

import java.io.Serializable;
import java.util.List;

public class Recipes implements Serializable {
    private int id;
    private int categoryId;
    private int authorId;
    private String name;
    private String origin;
    private int serves;
    private float calories;
    private float fat;
    private float protein;
    private float carbo;
    private List<String> imageInput;
    private String cookTime;
    private int createUser;
    private int updateUser;

    public Recipes() {
    }

    public Recipes(int id, int categoryId, int authorId, String name,
                   String origin, int serves, float calories, float fat, float protein,
                   float carbo, List<String> imageInput, String cookTime, int createUser, int updateUser) {
        this.id = id;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.name = name;
        this.origin = origin;
        this.serves = serves;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbo = carbo;
        this.imageInput = imageInput;
        this.cookTime = cookTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbo() {
        return carbo;
    }

    public void setCarbo(float carbo) {
        this.carbo = carbo;
    }

    public List<String> GetImageInput() {
        return imageInput;
    }

    public void setImageInput(List<String> imageInput) {
        this.imageInput = imageInput;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }
}
