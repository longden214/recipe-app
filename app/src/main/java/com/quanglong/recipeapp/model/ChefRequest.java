package com.quanglong.recipeapp.model;

import com.google.gson.annotations.SerializedName;

public class ChefRequest {
    @SerializedName("keyword")
    private String keyword;
    @SerializedName("email")
    private String email;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("userName")
    private String userName;
    @SerializedName("sex")
    private int sex;
    @SerializedName("role")
    private int role;
    @SerializedName("status")
    private int status;
    @SerializedName("sortByIdDESC")
    private boolean sortByIdDESC;
    @SerializedName("sortByTotalRecipeDESC")
    private boolean sortByTotalRecipeDESC;
    @SerializedName("sortByTotalFollowOtherUserDESC")
    private boolean sortByTotalFollowOtherUserDESC;
    @SerializedName("sortByTotalFollowedByOthersUserDESC")
    private boolean sortByTotalFollowedByOthersUserDESC;
    @SerializedName("sortByTotalViewsDESC")
    private boolean sortByTotalViewsDESC;
    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("pageSize")
    private int pageSize;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSortByIdDESC() {
        return sortByIdDESC;
    }

    public void setSortByIdDESC(boolean sortByIdDESC) {
        this.sortByIdDESC = sortByIdDESC;
    }

    public boolean isSortByTotalRecipeDESC() {
        return sortByTotalRecipeDESC;
    }

    public void setSortByTotalRecipeDESC(boolean sortByTotalRecipeDESC) {
        this.sortByTotalRecipeDESC = sortByTotalRecipeDESC;
    }

    public boolean isSortByTotalFollowOtherUserDESC() {
        return sortByTotalFollowOtherUserDESC;
    }

    public void setSortByTotalFollowOtherUserDESC(boolean sortByTotalFollowOtherUserDESC) {
        this.sortByTotalFollowOtherUserDESC = sortByTotalFollowOtherUserDESC;
    }

    public boolean isSortByTotalFollowedByOthersUserDESC() {
        return sortByTotalFollowedByOthersUserDESC;
    }

    public void setSortByTotalFollowedByOthersUserDESC(boolean sortByTotalFollowedByOthersUserDESC) {
        this.sortByTotalFollowedByOthersUserDESC = sortByTotalFollowedByOthersUserDESC;
    }

    public boolean isSortByTotalViewsDESC() {
        return sortByTotalViewsDESC;
    }

    public void setSortByTotalViewsDESC(boolean sortByTotalViewsDESC) {
        this.sortByTotalViewsDESC = sortByTotalViewsDESC;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ChefRequest(String keyword, String email, String phoneNumber, String displayName, String userName, int sex, int role, int status, boolean sortByIdDESC, boolean sortByTotalRecipeDESC, boolean sortByTotalFollowOtherUserDESC, boolean sortByTotalFollowedByOthersUserDESC, boolean sortByTotalViewsDESC, int pageIndex, int pageSize) {
        this.keyword = keyword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.userName = userName;
        this.sex = sex;
        this.role = role;
        this.status = status;
        this.sortByIdDESC = sortByIdDESC;
        this.sortByTotalRecipeDESC = sortByTotalRecipeDESC;
        this.sortByTotalFollowOtherUserDESC = sortByTotalFollowOtherUserDESC;
        this.sortByTotalFollowedByOthersUserDESC = sortByTotalFollowedByOthersUserDESC;
        this.sortByTotalViewsDESC = sortByTotalViewsDESC;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public ChefRequest() {
    }
}