package com.quanglong.recipeapp.responses;

import com.google.gson.annotations.SerializedName;
import com.quanglong.recipeapp.model.Notifications;

import java.util.List;

public class NotificationResponse {
    @SerializedName("totalPage")
    private int totalPage;

    @SerializedName("notificationViewModels")
    private List<Notifications> notificationViewModels;

    public int getTotalPage(){

        return totalPage;
    }
    public List<Notifications> getNotifications(){
        return notificationViewModels;
    }
}
