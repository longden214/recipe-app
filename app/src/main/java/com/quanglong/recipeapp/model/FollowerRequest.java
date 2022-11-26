package com.quanglong.recipeapp.model;

public class FollowerRequest {
    private int userId;
    private boolean isFollowing;
    private int pageIndex;
    private int pageSize;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
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

    public FollowerRequest(int userId, boolean isFollowing, int pageIndex, int pageSize) {
        this.userId = userId;
        this.isFollowing = isFollowing;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public FollowerRequest() {
    }
}
