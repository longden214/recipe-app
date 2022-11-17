package com.quanglong.recipeapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
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
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;

import com.quanglong.recipeapp.responses.CategoryResponses;

public class CatagoryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;
    private RecyclerView recyclerView;
    private ArrayList<Category> mlistCategory = new ArrayList<>();
    private CategoryViewModel viewModel;
    private CategoryAllAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        doInitialization();
        StatusBarConfig.StatusBarCustom(this);
        customActionBar();

        setCategoryAllRecycler(mlistCategory);
        getCategoryAll();
    }

    private void getCategoryAll() {
        viewModel.getCategoryWithParam("", true, true, false,
                true, 1, 10).observe(this, new Observer<CategoryResponses>() {
            @Override
            public void onChanged(CategoryResponses categoryResponses) {
                if (categoryResponses.getCategoryShow() != null) {
                    if (categoryResponses.getCategoryShow().size() > 0) {
                        int oldCount = mlistCategory.size();

                        mlistCategory.addAll(categoryResponses.getCategoryShow());
                        adapter.notifyItemRangeInserted(oldCount, mlistCategory.size());
                    }
                }
            }
        });
    }

    private void setCategoryAllRecycler(ArrayList<Category> mlistCategory) {
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);
        adapter = new CategoryAllAdapter(mlistCategory, this);
        recyclerView.setAdapter(adapter);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.rvMain);
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
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

}