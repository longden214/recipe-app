package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.repositories.CategoryRepository;
import com.quanglong.recipeapp.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<String> createUser(User user) {
        return userRepository.createUser(user);
    }
}
