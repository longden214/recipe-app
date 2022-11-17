package com.quanglong.recipeapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quanglong.recipeapp.model.ChefRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.repositories.PopularChefsRepository;
import com.quanglong.recipeapp.responses.PopularChefResponses;

import java.util.List;

public class PopularChefsViewModel extends ViewModel {
    private PopularChefsRepository popularChefsRepository;
    public PopularChefsViewModel(){
        popularChefsRepository = new PopularChefsRepository();
    }

    public LiveData<PopularChefResponses> getAllPopularChef(ChefRequest chefRequest){
        return popularChefsRepository.getallPopularChef(chefRequest);
    }
}
