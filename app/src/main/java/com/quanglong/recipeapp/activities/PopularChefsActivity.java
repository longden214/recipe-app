package com.quanglong.recipeapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.CategoryAllAdapter;
import com.quanglong.recipeapp.adapter.PopularAdapter;
import com.quanglong.recipeapp.adapter.PopularChefAdapter;
import com.quanglong.recipeapp.listener.UserListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.ChefRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.responses.CategoryResponses;
import com.quanglong.recipeapp.responses.PopularChefResponses;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;
import com.quanglong.recipeapp.viewmodels.PopularChefsViewModel;

import java.util.ArrayList;
import java.util.List;

public class PopularChefsActivity extends AppCompatActivity implements View.OnClickListener, UserListener {
    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView recyclerView;
    private ArrayList<PopularChef> mlistpopular = new ArrayList<>() ;
    private PopularChefsViewModel viewModel;
    private PopularChefAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private ProgressBar progressBar_loading;
    private ProgressBar progressBar_more;
    private boolean isLoading =false;
    private boolean isLoadingMore = false;
    private SharedPreferences userLocalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_chefs);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();
        setPopularRecycler(mlistpopular);
        getPopularChef();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    if(currentPage<totalAvailablePages){
                        currentPage +=1;
                        getPopularChef();
                    }
                }
            }
        });

    }

    private void setPopularRecycler(ArrayList<PopularChef> popularChefList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PopularChefAdapter(popularChefList,PopularChefsActivity.this,this);
        recyclerView.setAdapter(adapter);
    }

    private void getPopularChef() {
        toggleLoading();
        ChefRequest chefRequest = new ChefRequest();
        chefRequest.setKeyword("");
        chefRequest.setEmail("");
        chefRequest.setPhoneNumber("");
        chefRequest.setDisplayName("");
        chefRequest.setUserName("");
        chefRequest.setSex(-1);
        chefRequest.setRole(-1);
        chefRequest.setStatus(0);
        chefRequest.setSortByIdDESC(false);
        chefRequest.setSortByTotalRecipeDESC(true);
        chefRequest.setSortByTotalFollowOtherUserDESC(false);
        chefRequest.setSortByTotalFollowedByOthersUserDESC(true);
        chefRequest.setSortByTotalViewsDESC(true);
        chefRequest.setPageIndex(currentPage);
        chefRequest.setPageSize(10);
        chefRequest.setLoginUserId(userLocalDatabase.getInt("id", -1));
        viewModel.getAllPopularChef(chefRequest).observe(this, new Observer<PopularChefResponses>() {
            @Override
            public void onChanged(PopularChefResponses popularChefResponses) {
                if (popularChefResponses.getPopularShow() != null) {
                    totalAvailablePages = popularChefResponses.getTotalPage();
                    if (popularChefResponses.getPopularShow().size() > 0) {
                        if(currentPage==1){
                            isLoading=true;
                        }else {
                            isLoadingMore=true;
                        }
                        toggleLoading();
                        int oldCount = mlistpopular.size();
                        mlistpopular.addAll(popularChefResponses.getPopularShow());
                        adapter.notifyItemRangeInserted(oldCount, mlistpopular.size());
                    }
                }
            }
        });

    }

    private void toggleLoading(){
        if(currentPage ==1){
            if(isLoading){
                progressBar_loading.setVisibility(View.GONE);
                isLoading=false;
            }else {
                progressBar_loading.setVisibility(View.VISIBLE);
            }
        }else {
            if(isLoadingMore){
                progressBar_more.setVisibility(View.GONE);
                isLoadingMore = false;
            }else {
                progressBar_more.setVisibility(View.VISIBLE);
            }
        }
    }


    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        recyclerView = findViewById(R.id.rvMain);
        viewModel = new ViewModelProvider(this).get(PopularChefsViewModel.class);
        progressBar_loading = findViewById(R.id.progressBar_loading);
        progressBar_more = findViewById(R.id.progressBar_more);
        this.userLocalDatabase = this.getSharedPreferences("userDetails", 0);
    }

    private void customActionBar() {

        setSupportActionBar(toolbar);
        this.tv_title.setText("Popular chefs");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserClicked(PopularChef chef) {
        Intent intent = new Intent(PopularChefsActivity.this, UserProfileActivity.class);
        intent.putExtra("chef",chef);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}