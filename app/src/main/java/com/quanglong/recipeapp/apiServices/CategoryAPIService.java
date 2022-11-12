package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPIService {
    @GET("category/getAll")
    Call<List<Category>> getAllCategory();
}
