package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.ChefRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.responses.PopularChefResponses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PopularChefsAPIService {
    @POST("users/filterData")
    Call<PopularChefResponses> popularchef(@Body ChefRequest chefRequest);

}
