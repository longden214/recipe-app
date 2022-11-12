package com.quanglong.recipeapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.CategoryAdapter;
import com.quanglong.recipeapp.entity.Category;

import java.util.ArrayList;

public class CatagoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Category> mlistCategory = new ArrayList<>();

    private void FakeData(){
        mlistCategory.add(new Category("Sản Phẩm 1","2", R.drawable.rectangle_646));
        mlistCategory.add(new Category("Sản Phẩm 2","0", R.drawable.avatar));
        mlistCategory.add(new Category("Sản Phẩm 3","4", R.drawable.avatar));
        mlistCategory.add(new Category("Sản Phẩm 4","6", R.drawable.back_icon));
        mlistCategory.add(new Category("Sản Phẩm 5","1", R.drawable.rectangle_646));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);

        FakeData();

        recyclerView = (RecyclerView) findViewById(R.id.rvMain);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);
        CategoryAdapter adapter = new CategoryAdapter(mlistCategory,this);

        recyclerView.setAdapter(adapter);
    }



}