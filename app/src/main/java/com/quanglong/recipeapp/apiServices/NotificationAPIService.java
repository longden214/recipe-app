package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.responses.NotificationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
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

    @PUT("notification/readNotification")
    Call<String> readNotification(@Query("id") int id);

    @PUT("notification/unReadNotification")
    Call<String> unreadNotification(@Query("id") int id);

    @DELETE("notification/delete")
    Call<String> removeNotification(@Query("id") int id);
}
