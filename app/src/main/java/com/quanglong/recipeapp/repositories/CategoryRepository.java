package com.quanglong.recipeapp.repositories;

import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quanglong.recipeapp.apiServices.CategoryAPIService;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.network.ApiClient;
import com.quanglong.recipeapp.responses.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class CategoryRepository {
    private CategoryAPIService apiService;

    public CategoryRepository() {
        apiService = ApiClient.getRetrofit().create(CategoryAPIService.class);
    }

    public LiveData<List<Category>> getAllCategory(){
        MutableLiveData<List<Category>> data = new MutableLiveData<>();

        apiService.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<CategoryResponse> getCategoryWithParam(String keyword, boolean isGetAll, boolean sortIdDESC,
                                                           boolean sortNameASC, boolean sortTotalRecipeDESC, int pageIndex, int pageSize){
        MutableLiveData<CategoryResponse> data = new MutableLiveData<>();

        apiService.getCategoryWithParam(keyword,isGetAll,sortIdDESC,sortNameASC,sortTotalRecipeDESC,pageIndex,pageSize).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
