package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryAPIService {
    @GET("category/getAll")
    Call<List<Category>> getAllCategory();

    @GET("category/getLists")
    Call<List<Category>> getCategoryWithParam(@Query("keyword") String keyword,
                                              @Query("isGetAll") boolean isGetAll,
                                              @Query("sortIdDESC") boolean sortIdDESC,
                                              @Query("sortNameASC") boolean sortNameASC,
                                              @Query("sortTotalRecipeDESC") boolean sortTotalRecipeDESC,
                                              @Query("pageIndex") int pageIndex,
                                              @Query("pageSize") int pageSize
                                              );
}
