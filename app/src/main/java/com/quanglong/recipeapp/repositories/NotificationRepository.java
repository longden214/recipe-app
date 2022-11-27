package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.NotificationAPIService;
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.NotificationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRepository {
    private NotificationAPIService apiService;

    public NotificationRepository() {
        apiService = ApiClient.getRetrofit().create(NotificationAPIService.class);
    }

    public LiveData<List<Notifications>> getAllNotification(){
        MutableLiveData<List<Notifications>> data = new MutableLiveData<>();

        apiService.getAllNotification().enqueue(new Callback<List<Notifications>>() {
            @Override
            public void onResponse(Call<List<Notifications>> call, Response<List<Notifications>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Notifications>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<NotificationResponse> getNotificationWithParam(int userId,int status,int pageIndex, int pageSize){
        MutableLiveData<NotificationResponse> data = new MutableLiveData<>();

        apiService.getNotificationWithParam(userId,status,pageIndex,pageSize).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> readNotification(int id){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.readNotification(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> unreadNotification(int id){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.unreadNotification(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> removeNotification(int id){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.removeNotification(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
