package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.FolloweService;
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.PopularChefResponses;
import com.quanglong.recipeapp.responses.RecipeAddResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerRepository {
    private FolloweService followeService;
    public FollowerRepository(){
        followeService = ApiClient.getRetrofit().create(FolloweService.class);
    }

    public LiveData<PopularChefResponses> getFollowerWithParam(int userId,boolean isFollowing,int pageIndex,int pageSize){
        MutableLiveData<PopularChefResponses> data = new MutableLiveData<>();
        followeService.getFollowerWithParam(userId, isFollowing, pageIndex, pageSize).enqueue(new Callback<PopularChefResponses>() {
            @Override
            public void onResponse(Call<PopularChefResponses> call, Response<PopularChefResponses> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PopularChefResponses> call, Throwable t) {
                    data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<PopularChefResponses> getFollowingWithParam(int userId,boolean isFollowing,int pageIndex,int pageSize,int loginUserId){
        MutableLiveData<PopularChefResponses> data = new MutableLiveData<>();
        followeService.getFollowingWithParam(userId, isFollowing, pageIndex, pageSize,loginUserId).enqueue(new Callback<PopularChefResponses>() {
            @Override
            public void onResponse(Call<PopularChefResponses> call, Response<PopularChefResponses> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PopularChefResponses> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<RecipeAddResponse> saveFollow(FollowRequest followRequest){
        MutableLiveData<RecipeAddResponse> data = new MutableLiveData<>();
        followeService.saveFollow(followRequest).enqueue(new Callback<RecipeAddResponse>() {
            @Override
            public void onResponse(Call<RecipeAddResponse> call, Response<RecipeAddResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeAddResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<String> unFollow(int userId, int followerId){
        MutableLiveData<String> data = new MutableLiveData<>();

        followeService.unFollow(userId,followerId).enqueue(new Callback<String>() {
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
