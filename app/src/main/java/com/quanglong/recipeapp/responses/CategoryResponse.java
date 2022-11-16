package com.quanglong.recipeapp.responses;

import com.google.gson.annotations.SerializedName;
import com.quanglong.recipeapp.model.Category;

import java.util.List;

public class CategoryResponse {
    @SerializedName("totalPage")
    private int totalPage;

    @SerializedName("categoryViewModels")
    private List<Category> categoryViewModels;

    public int getTotalPage() {
        return totalPage;
    }

    public List<Category> getCaregoties() {
        return categoryViewModels;
    }
}
