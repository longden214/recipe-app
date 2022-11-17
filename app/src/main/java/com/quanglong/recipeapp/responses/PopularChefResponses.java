package com.quanglong.recipeapp.responses;

import com.google.gson.annotations.SerializedName;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.PopularChef;

import java.util.List;

public class PopularChefResponses {
    @SerializedName("totalPage")
    private int totalPage;

    @SerializedName("usersViewModels")
    private List<PopularChef> usersViewModels;

    public int getTotalPage(){
        return totalPage;
    }
    public List<PopularChef> getPopularShow(){
        return usersViewModels;
    }
}
