package com.quanglong.recipeapp.model;

public class FollowingRequest {
    private int followerId;
    private boolean isFollowing;
    private int pageIndex;
    private int pageSize;
    private int loginUserId;

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
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

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
    }

    public FollowingRequest(int followerId, boolean isFollowing, int pageIndex, int pageSize, int loginUserId) {
        this.followerId = followerId;
        this.isFollowing = isFollowing;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.loginUserId = loginUserId;
    }

    public FollowingRequest() {
    }
}
