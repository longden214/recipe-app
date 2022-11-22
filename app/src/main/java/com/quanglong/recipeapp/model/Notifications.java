package com.quanglong.recipeapp.model;

import com.google.gson.annotations.SerializedName;

public class Notifications {
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("createUserDisplay")
    private String createUserDisplay;
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("notificationType")
    private String notificationType;
    @SerializedName("status")
    private int status;
    @SerializedName("userDisplay")
    private String userDisplay;
    @SerializedName("userId")
    private int userId;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserDisplay() {
        return userDisplay;
    }

    public void setUserDisplay(String userDisplay) {
        this.userDisplay = userDisplay;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Notifications(String createDate, String createUserDisplay, String description, int id, String notificationType, int status, String userDisplay, int userId) {
        this.createDate = createDate;
        this.createUserDisplay = createUserDisplay;
        this.description = description;
        this.id = id;
        this.notificationType = notificationType;
        this.status = status;
        this.userDisplay = userDisplay;
        this.userId = userId;
    }

    public Notifications() {
    }
}
