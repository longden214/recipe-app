package com.quanglong.recipeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Category {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("status")
    private int status;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("createUser")
    private int createUser;

    @SerializedName("updateUser")
    private int updateUser;

    @SerializedName("type")
    private String type;

    @SerializedName("createUserDisplay")
    private String createUserDisplay;

    @SerializedName("totalRecipes")
    private int totalRecipes;

    public Category() {
    }

    public Category(int id, String name, String image, int status, String createDate,
                    int createUser, int updateUser, String type, String createUserDisplay, int totalRecipes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.type = type;
        this.createUserDisplay = createUserDisplay;
        this.totalRecipes = totalRecipes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public int getTotalRecipes() {
        return totalRecipes;
    }

    public void setTotalRecipes(int totalRecipes) {
        this.totalRecipes = totalRecipes;
    }
}
