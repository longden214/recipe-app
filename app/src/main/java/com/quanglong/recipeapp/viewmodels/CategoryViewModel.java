package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.repositories.CategoryRepository;

import java.util.List;

import com.quanglong.recipeapp.responses.CategoryResponses;

public class CategoryViewModel extends ViewModel {
    private CategoryRepository categoryRepository;

    public CategoryViewModel() {
        categoryRepository = new CategoryRepository();
    }

    public LiveData<List<Category>> getAllCategory() {

        return categoryRepository.getAllCategory();
    }

    public LiveData<CategoryResponses> getCategoryWithParam(String keyword, boolean isGetAll, boolean sortIdDESC,
                                                            boolean sortNameASC, boolean sortTotalRecipeDESC, int pageIndex, int pageSize) {

        return categoryRepository.getCategoryWithParam(keyword,isGetAll,sortIdDESC,sortNameASC,sortTotalRecipeDESC,pageIndex,pageSize);
    }
}
