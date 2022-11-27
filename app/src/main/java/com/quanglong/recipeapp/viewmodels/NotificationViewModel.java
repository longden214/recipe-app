package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.repositories.NotificationRepository;
import com.quanglong.recipeapp.responses.NotificationResponse;

import java.util.List;

public class NotificationViewModel extends ViewModel {
    private NotificationRepository notificationReponsitory;
    public NotificationViewModel(){
        notificationReponsitory = new NotificationRepository();
    }

    public LiveData<List<Notifications>> getAllNotification(){
        return notificationReponsitory.getAllNotification();
    }
    public LiveData<NotificationResponse> getNotificationWithParam(int userId,int status,int pageIndex,int pageSize){
        return notificationReponsitory.getNotificationWithParam(userId,status,pageIndex,pageSize);
    }

    public LiveData<String> readNotification(int id){
        return notificationReponsitory.readNotification(id);
    }

    public LiveData<String> unreadNotification(int id){
        return notificationReponsitory.unreadNotification(id);
    }

    public LiveData<String> removeNotification(int id){
        return notificationReponsitory.removeNotification(id);
    }
}
