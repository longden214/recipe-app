package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.repositories.FollowerRepository;
import com.quanglong.recipeapp.responses.PopularChefResponses;
import com.quanglong.recipeapp.responses.RecipeAddResponse;

import java.util.List;

public class FollowerViewModel extends ViewModel {
    private FollowerRepository followerRepository;
    public FollowerViewModel(){
        followerRepository = new FollowerRepository();
    }
    public LiveData<PopularChefResponses> getFollowerWithParam(int userId, boolean isFollowing, int pageIndex, int pageSize){
        return followerRepository.getFollowerWithParam(userId, isFollowing, pageIndex, pageSize);
    }

    public LiveData<PopularChefResponses> getFollowingWithParam(int followerId, boolean isFollowing, int pageIndex, int pageSize,int loginUserId){
        return followerRepository.getFollowingWithParam(followerId, isFollowing, pageIndex, pageSize,loginUserId);
    }
    public LiveData<RecipeAddResponse> saveFollow(FollowRequest followRequest){
        return followerRepository.saveFollow(followRequest);
    }

    public LiveData<String> unFollow(int userId, int followerId){
        return followerRepository.unFollow(userId,followerId);
    }

}
