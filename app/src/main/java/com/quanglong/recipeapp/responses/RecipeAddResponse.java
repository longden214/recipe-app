package com.quanglong.recipeapp.responses;

import com.quanglong.recipeapp.model.Notifications;

import java.util.ArrayList;
import java.util.List;

public class RecipeAddResponse {
    private String message;
    private List<Notifications> notificationModels;

    public RecipeAddResponse() {
    }

    public RecipeAddResponse(String message) {
        this.message = message;
        this.notificationModels = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Notifications> getNotificationModels() {
        return notificationModels;
    }

    public void setNotificationModels(List<Notifications> notificationModels) {
        this.notificationModels = notificationModels;
    }

    public RecipeAddResponse(String message, List<Notifications> notificationModels) {
        this.message = message;
        this.notificationModels = notificationModels;
    }
}
