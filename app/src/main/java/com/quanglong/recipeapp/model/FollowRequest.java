package com.quanglong.recipeapp.model;

public class FollowRequest {
    private int userId;
    private int followerId;

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public FollowRequest(int userId, int followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    public FollowRequest() {
    }
}
