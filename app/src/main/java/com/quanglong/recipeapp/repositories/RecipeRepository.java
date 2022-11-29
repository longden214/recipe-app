package com.quanglong.recipeapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.RecipeService;
import com.quanglong.recipeapp.model.RatingRequest;
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.apiServices.UserService;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.SaveRecipeRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.responses.RecipeResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class RecipeRepository {
    private RecipeService apiService;

    public RecipeRepository() {
        apiService = ApiClient.getRetrofit().create(RecipeService.class);
    }

    public LiveData<RecipeResponse> getAllNewRecipe(RecipeRequest newRequest){
        MutableLiveData<RecipeResponse> data = new MutableLiveData<>();
        apiService.newrecipe(newRequest).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<RecipeResponse> getSaveRecipe(int userId, int pageIndex, int pageSize){
        MutableLiveData<RecipeResponse> data = new MutableLiveData<>();
        apiService.getSaveRecipe(userId,pageIndex,pageSize).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<RecipeAddResponse> RecipeInsert(RecipeDataRequest dataRequest){
        MutableLiveData<RecipeAddResponse> data = new MutableLiveData<>();

        apiService.RecipeInsert(dataRequest).enqueue(new Callback<RecipeAddResponse>() {
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


    public LiveData<RecipeDetailResponse> getRecipeDetailWithParam(int id,int loginUserId){
        MutableLiveData<RecipeDetailResponse> data = new MutableLiveData<>();

        apiService.getRecipeDetailWithParam(id,loginUserId).enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<String> RecipeDelete(int recipe_id, int user_id){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.RecipeDelete(recipe_id,user_id).enqueue(new Callback<String>() {
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

    public LiveData<RecipeAddResponse> recipeRating(RatingRequest request){
        MutableLiveData<RecipeAddResponse> data = new MutableLiveData<>();

        apiService.recipeRating(request).enqueue(new Callback<RecipeAddResponse>() {
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

    public LiveData<String> saveRecipe(SaveRecipeRequest saveRecipeRequest){
        MutableLiveData<String> data = new MutableLiveData<>();
        apiService.saveRicpe(saveRecipeRequest).enqueue(new Callback<String>() {
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

    public LiveData<String> unRecipe(int recipeId, int userId){
        MutableLiveData<String> data = new MutableLiveData<>();

        apiService.unRecipe(recipeId,userId).enqueue(new Callback<String>() {
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
