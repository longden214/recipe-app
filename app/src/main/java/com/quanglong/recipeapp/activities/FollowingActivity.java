package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.FollowerAdapter;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.responses.PopularChefResponses;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.FollowerViewModel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class FollowingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView recyclerView;
    private ArrayList<PopularChef> mlistPopular = new ArrayList<>();
    private FollowerViewModel followerViewModel;
    private FollowerAdapter followerAdapter;
    private int id;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private ProgressBar progressBar_loading;
    private ProgressBar progressBar_more;
    private boolean isLoading =false;
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();
        id = getIntent().getIntExtra("id",-1);
        setFollowing(mlistPopular);
        getFollowing();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    if(currentPage<totalAvailablePages){
                        currentPage +=1;
                        getFollowing();
                    }
                }
            }
        });
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("Following");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFollowing(ArrayList<PopularChef> mlistPopular){
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);
        followerAdapter = new FollowerAdapter(mlistPopular,FollowingActivity.this);
        recyclerView.setAdapter(followerAdapter);
    }
    private void getFollowing(){
        toggleLoading();
        followerViewModel.getFollowerWithParam(id,true,currentPage,10).observe(this, new Observer<PopularChefResponses>() {
            @Override
            public void onChanged(PopularChefResponses popularChefResponses) {
                if(popularChefResponses != null){
                    totalAvailablePages = popularChefResponses.getTotalPage();
                    if(currentPage==1){
                        isLoading=true;
                    }else {
                        isLoadingMore=true;
                    }
                    toggleLoading();
                    if(popularChefResponses.getPopularShow().size()>0){
                        int oldCount = mlistPopular.size();
                        mlistPopular.addAll(popularChefResponses.getPopularShow());
                        followerAdapter.notifyItemRangeInserted(oldCount,mlistPopular.size());
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
        recyclerView = (RecyclerView) findViewById(R.id.rvMain);
        followerViewModel = new ViewModelProvider(this).get(FollowerViewModel.class);
        progressBar_loading = findViewById(R.id.progressBar_loading);
        progressBar_more = findViewById(R.id.progressBar_more);
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

}