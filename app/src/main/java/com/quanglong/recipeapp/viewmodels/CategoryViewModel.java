package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.repositories.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private CategoryRepository categoryRepository;

    public CategoryViewModel() {
        categoryRepository = new CategoryRepository();
    }

    public LiveData<List<Category>> getAllCategory() {
        return categoryRepository.getAllCategory();
    }
}