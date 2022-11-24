package com.quanglong.recipeapp.model;

import java.io.Serializable;

public class Recipe implements Serializable {
    private int authorId;
    private float calories;
    private float carbo;
    private  int categoryId;
    private String cookTime;
    private String createDate;
    private int createUser;
    private float fat;
    private int id;
    private String image;
    private String name;
    private String origin;
    private float protein;
    private int serves;
    private int status;
    private int totalViews;
    private int updateUser;
    private String author;
    private String authorAvatar;
    private float avgRating;
    private String categoryDisplay;
    private String createUserDisplay;
    private int totalRating;
    private boolean isSaveRecipe;
    private boolean isFollowAuthor;

    public boolean isFollowAuthor() {
        return isFollowAuthor;
    }

    public void setFollowAuthor(boolean followAuthor) {
        isFollowAuthor = followAuthor;
    }

    public boolean isSaveRecipe() {
        return isSaveRecipe;
    }

    public void setSaveRecipe(boolean saveRecipe) {
        isSaveRecipe = saveRecipe;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getCarbo() {
        return carbo;
    }

    public void setCarbo(float carbo) {
        this.carbo = carbo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public String getCategoryDisplay() {
        return categoryDisplay;
    }

    public void setCategoryDisplay(String categoryDisplay) {
        this.categoryDisplay = categoryDisplay;
    }

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public Recipe(int authorId, float calories, float carbo, int categoryId, String cookTime, String createDate, int createUser, float fat, int id, String image, String name, String origin, float protein, int serves, int status, int totalViews, int updateUser, String author, String authorAvatar, float avgRating,
                  String categoryDisplay, String createUserDisplay, int totalRating, boolean isSaveRecipe, boolean isFollowAuthor) {
        this.authorId = authorId;
        this.calories = calories;
        this.carbo = carbo;
        this.categoryId = categoryId;
        this.cookTime = cookTime;
        this.createDate = createDate;
        this.createUser = createUser;
        this.fat = fat;
        this.id = id;
        this.image = image;
        this.name = name;
        this.origin = origin;
        this.protein = protein;
        this.serves = serves;
        this.status = status;
        this.totalViews = totalViews;
        this.updateUser = updateUser;
        this.author = author;
        this.authorAvatar = authorAvatar;
        this.avgRating = avgRating;
        this.categoryDisplay = categoryDisplay;
        this.createUserDisplay = createUserDisplay;
        this.totalRating = totalRating;
        this.isSaveRecipe = isSaveRecipe;
        this.isFollowAuthor = isFollowAuthor;
    }

    public Recipe() {
    }
}
