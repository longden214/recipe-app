package com.quanglong.recipeapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.quanglong.recipeapp.responses.UserLoginResponse;

public class UserLocalStore {
    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(UserLoginResponse user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("address", user.getAddress());
        userLocalDatabaseEditor.putString("avatar", user.getAvatar());
        userLocalDatabaseEditor.putString("description", user.getDescription());
        userLocalDatabaseEditor.putString("displayName", user.getDisplayName());
        userLocalDatabaseEditor.putString("email", user.getEmail());
        userLocalDatabaseEditor.putInt("id", user.getId());
        userLocalDatabaseEditor.putString("job", user.getJob());
        userLocalDatabaseEditor.putString("phoneNumber", user.getPhoneNumber());
        userLocalDatabaseEditor.putString("userName", user.getUserName());
        userLocalDatabaseEditor.putInt("sex", user.getSex());
        userLocalDatabaseEditor.putInt("totalFollowOtherUser", user.getTotalFollowOtherUser());
        userLocalDatabaseEditor.putInt("totalFollowedByOthersUser", user.getTotalFollowedByOthersUser());
        userLocalDatabaseEditor.putInt("totalRecipe", user.getTotalRecipe());

        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public UserLoginResponse getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String address = userLocalDatabase.getString("address", "");
        String avatar = userLocalDatabase.getString("avatar", "");
        String description = userLocalDatabase.getString("description", "");
        String displayName = userLocalDatabase.getString("displayName", "");
        String email = userLocalDatabase.getString("email", "");
        int id = userLocalDatabase.getInt("id", -1);
        String job = userLocalDatabase.getString("job", "");
        String phoneNumber = userLocalDatabase.getString("phoneNumber", "");
        String userName = userLocalDatabase.getString("userName", "");
        int sex = userLocalDatabase.getInt("sex", -1);
        int totalFollowOtherUser = userLocalDatabase.getInt("totalFollowOtherUser", -1);
        int totalFollowedByOthersUser = userLocalDatabase.getInt("totalFollowedByOthersUser", -1);
        int totalRecipe = userLocalDatabase.getInt("totalRecipe", -1);

        UserLoginResponse user = new UserLoginResponse();
        user.setAddress(address);
        user.setAvatar(avatar);
        user.setDescription(description);
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setId(id);
        user.setJob(job);
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setSex(sex);
        user.setTotalFollowOtherUser(totalFollowOtherUser);
        user.setTotalFollowedByOthersUser(totalFollowedByOthersUser);
        user.setTotalRecipe(totalRecipe);

        return user;
    }
}
