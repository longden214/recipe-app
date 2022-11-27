package com.quanglong.recipeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditProfileRequest {
    @SerializedName("id")
    private int id;
    @SerializedName("userName")
    private String userName;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("sex")
    private int sex;
    @SerializedName("imageInput")
    private List<String> imageInput;
    @SerializedName("description")
    private String description;

    public EditProfileRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public List<String> getImageInput() {
        return imageInput;
    }

    public void setImageInput(List<String> imageInput) {
        this.imageInput = imageInput;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
