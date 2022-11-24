package com.quanglong.recipeapp.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserLoginResponse {
    @SerializedName("type")
    private String type;

    @SerializedName("address")
    private String address;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("createDate")
    private Date createDate;

    @SerializedName("createUser")
    private int createUser;

    @SerializedName("description")
    private String description;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("email")
    private String email;

    @SerializedName("id")
    private int id;

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

    @SerializedName("updateUser")
    private int updateUser;

    @SerializedName("userName")
    private String userName;

    @SerializedName("isFollowerUser")
    private boolean isFollowerUser;

    @SerializedName("message")
    private String message;

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("totalRecipe")
    private int totalRecipe;

    @SerializedName("totalViews")
    private int totalViews;

    @SerializedName("totalFollowOtherUser")
    private int totalFollowOtherUser;

    @SerializedName("totalFollowedByOthersUser")
    private int totalFollowedByOthersUser;

    public UserLoginResponse() {
    }

    public UserLoginResponse(String type, String address, String avatar, Date createDate, int createUser, String description, String displayName, String email, int id, String job, String phoneNumber, int role, int sex, int status, int updateUser, String userName, boolean isFollowerUser, String message, int statusCode, int totalRecipe, int totalViews, int totalFollowOtherUser, int totalFollowedByOthersUser) {
        this.type = type;
        this.address = address;
        this.avatar = avatar;
        this.createDate = createDate;
        this.createUser = createUser;
        this.description = description;
        this.displayName = displayName;
        this.email = email;
        this.id = id;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.sex = sex;
        this.status = status;
        this.updateUser = updateUser;
        this.userName = userName;
        this.isFollowerUser = isFollowerUser;
        this.message = message;
        this.statusCode = statusCode;
        this.totalRecipe = totalRecipe;
        this.totalViews = totalViews;
        this.totalFollowOtherUser = totalFollowOtherUser;
        this.totalFollowedByOthersUser = totalFollowedByOthersUser;
    }

    public boolean isFollowerUser() {
        return isFollowerUser;
    }

    public void setFollowerUser(boolean followerUser) {
        isFollowerUser = followerUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
}
