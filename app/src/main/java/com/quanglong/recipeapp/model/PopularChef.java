package com.quanglong.recipeapp.model;

import com.google.gson.annotations.SerializedName;

public class PopularChef {
    @SerializedName("id")
    private int id;
    @SerializedName("address")
    private String address;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("createUser")
    private int createUser;
    @SerializedName("description")
    private String description;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("email")
    private String email;
    @SerializedName("job")
    private String job;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("role")
    private int role;
    @SerializedName("sex")
    private int sex;
    @SerializedName("status")
    private int status;
    @SerializedName("updateDate")
    private String updateDate;
    @SerializedName("updateUser")
    private int updateUser;
    @SerializedName("userName")
    private String userName;
    @SerializedName("totalFollowOtherUser")
    private int totalFollowOtherUser;
    @SerializedName("totalFollowedByOthersUser")
    private int totalFollowedByOthersUser;
    @SerializedName("totalRecipe")
    private int totalRecipe;
    @SerializedName("totalViews")
    private int totalViews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalFollowOtherUser() {
        return totalFollowOtherUser;
    }

    public void setTotalFollowOtherUser(int totalFollowOtherUser) {
        this.totalFollowOtherUser = totalFollowOtherUser;
    }

    public int getTotalFollowedByOthersUser() {
        return totalFollowedByOthersUser;
    }

    public void setTotalFollowedByOthersUser(int totalFollowedByOthersUser) {
        this.totalFollowedByOthersUser = totalFollowedByOthersUser;
    }

    public int getTotalRecipe() {
        return totalRecipe;
    }

    public void setTotalRecipe(int totalRecipe) {
        this.totalRecipe = totalRecipe;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public PopularChef(int id, String address, String avatar, String createDate, int createUser, String description, String displayName, String email, String job, String phoneNumber, int role, int sex, int status, String updateDate, int updateUser, String userName, int totalFollowOtherUser, int totalFollowedByOthersUser, int totalRecipe, int totalViews) {
        this.id = id;
        this.address = address;
        this.avatar = avatar;
        this.createDate = createDate;
        this.createUser = createUser;
        this.description = description;
        this.displayName = displayName;
        this.email = email;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.sex = sex;
        this.status = status;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.userName = userName;
        this.totalFollowOtherUser = totalFollowOtherUser;
        this.totalFollowedByOthersUser = totalFollowedByOthersUser;
        this.totalRecipe = totalRecipe;
        this.totalViews = totalViews;
    }

    public PopularChef() {
    }
}
