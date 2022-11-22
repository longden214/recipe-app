package com.quanglong.recipeapp.activities;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.CategoryAllAdapter;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.responses.CategoryResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CatagoryActivity extends AppCompatActivity implements View.OnClickListener, CategoryListener {
    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView recyclerView;
    private ArrayList<Category> mlistCategory = new ArrayList<>();
    private CategoryViewModel viewModel;
    private CategoryAllAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private ProgressBar progressBar_loading;
    private ProgressBar progressBar_more;
    private boolean isLoading = false;
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();

        setCategoryAllRecycler(mlistCategory);
        getCategoryAll();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    if (currentPage < totalAvailablePages) {
                        currentPage += 1;
                        getCategoryAll();
                    }
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            // dữ liêu server trả về thành công
            if (isLoading) {
                progressBar_loading.setVisibility(View.GONE);
                isLoading = false;
            } else {// đang trong quá trình load dữ liệu từ server
                progressBar_loading.setVisibility(View.VISIBLE);
            }
        } else {
            // dữ liêu server page tiếp theo trả về thành công
            if (isLoadingMore) {
                progressBar_more.setVisibility(View.GONE);
                isLoadingMore = false;
            } else {
                progressBar_more.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getCategoryAll() {
        toggleLoading();
        viewModel.getCategoryWithParam("", true, true, false,
                true, currentPage, 10).observe(this, new Observer<CategoryResponse>() {
            @Override
            public void onChanged(CategoryResponse categories) {
                if (categories != null) {
                    totalAvailablePages = categories.getTotalPage();
                    if (categories.getCaregoties().size() > 0) {
                        if (currentPage == 1) {
                            isLoading = true;
                        } else {
                            isLoadingMore = true;
                        }
                        toggleLoading();
                        int oldCount = mlistCategory.size();

                        mlistCategory.addAll(categories.getCaregoties());
                        adapter.notifyItemRangeInserted(oldCount, mlistCategory.size());
                    }
                }
            }
        });
    }

    private void setCategoryAllRecycler(ArrayList<Category> mlistCategory) {
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);
        adapter = new CategoryAllAdapter(mlistCategory,CatagoryActivity.this, this);
        recyclerView.setAdapter(adapter);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.rvMain);
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        progressBar_loading = findViewById(R.id.progressBar_loading);
        progressBar_more = findViewById(R.id.progressBar_more);
    }

    private void customActionBar() {

        setSupportActionBar(toolbar);
        this.tv_title.setText("Categories");
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
    public void onCategoryClicked(int cate_id, String cate_name) {
        Intent intent = new Intent(CatagoryActivity.this,RecipeByCategoryActivity.class);
        intent.putExtra("cateId",cate_id);
        intent.putExtra("cateName",cate_name);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

    }
}