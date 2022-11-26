package com.quanglong.recipeapp.apiServices;

import com.quanglong.recipeapp.responses.PopularChefResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FolloweService {
    @GET("users/getListFollowOtherUser")
    Call<PopularChefResponses> getFollowerWithParam(@Query("userId") int userId,
                                                    @Query("isFollowing") boolean isFollowing,
                                                    @Query("pageIndex") int pageIndex,
                                                    @Query("pageSize") int pageSize
    );
    @GET("users/getListFollowedByOthersUser")
    Call<PopularChefResponses> getFollowingWithParam(@Query("followerId") int followerId,
                                                    @Query("isFollowing") boolean isFollowing,
                                                    @Query("pageIndex") int pageIndex,
                                                    @Query("pageSize") int pageSize,
                                                     @Query("loginUserId") int loginUserId
    );
}
