package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.responses.NotificationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NotificationAPIService {
    @GET("notification/getAll")
    Call<List<Notifications>> getAllNotification();
    @GET("notification/getNotificationByUserId")
    Call<NotificationResponse> getNotificationWithParam(@Query("userId") int userId,
                                                        @Query("status") int status,
                                                        @Query("pageIndex") int pageIndex,
                                                        @Query("pageSize") int pageSize
    );
}
