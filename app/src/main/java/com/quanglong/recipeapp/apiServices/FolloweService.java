package com.quanglong.recipeapp.apiServices;
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.responses.PopularChefResponses;
import com.quanglong.recipeapp.responses.RecipeAddResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @POST("followers")
    Call<RecipeAddResponse> saveFollow(@Body FollowRequest followRequest);

    @DELETE("followers/unfollow")
    Call<String> unFollow(@Query("userId") int userId,
                                        @Query("followerId") int followerId);
}
