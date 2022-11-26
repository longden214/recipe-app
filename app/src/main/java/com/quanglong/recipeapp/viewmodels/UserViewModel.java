package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.AccountSettingRequest;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.EditProfileRequest;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.PasswordRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.repositories.CategoryRepository;
import com.quanglong.recipeapp.repositories.UserRepository;
import com.quanglong.recipeapp.responses.UserLoginResponse;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<String> createUser(User user) {
        return userRepository.createUser(user);
    }

    public LiveData<String> editProfile(EditProfileRequest request) {
        return userRepository.editProfile(request);
    }

    public LiveData<String> accountSetting(AccountSettingRequest request) {
        return userRepository.accountSetting(request);
    }

    public LiveData<String> updaePassword(PasswordRequest request) {
        return userRepository.updatePassword(request);
    }

    public LiveData<UserLoginResponse> login(LoginRequest loginRequest) {
        return userRepository.login(loginRequest);
    }
}
