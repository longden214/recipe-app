package com.quanglong.recipeapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.NewRecipeActivity;
import com.quanglong.recipeapp.activities.PopularChefsActivity;
import com.quanglong.recipeapp.activities.SearchActivity;
import com.quanglong.recipeapp.activities.TrendingRecipeActivity;
import com.quanglong.recipeapp.adapter.CategoryAdapter;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import com.quanglong.recipeapp.activities.CatagoryActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout btn_filter;
    private CategoryViewModel viewModel;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView category_recycler;
    private LinearLayout category_see_all;
    private LinearLayout chef_see_all;
    private LinearLayout new_see_all;
    private LinearLayout trending_see_all;
    private EditText edt_search;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doInitialization(view);

        btn_filter.setOnClickListener(this);
        category_see_all.setOnClickListener(this);
        chef_see_all.setOnClickListener(this);
        new_see_all.setOnClickListener(this);
        trending_see_all.setOnClickListener(this);

        edt_search.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

                return false;
            }
        });

        setCategoryRecycler(categoryList);
        getAllCategory();
    }

    private void doInitialization(View view) {
        category_see_all = view.findViewById(R.id.category_see_all);
        chef_see_all = view.findViewById(R.id.chef_see_all);
        new_see_all = view.findViewById(R.id.new_see_all);
        trending_see_all = view.findViewById(R.id.trending_see_all);
        category_recycler = view.findViewById(R.id.category_list);
        edt_search = view.findViewById(R.id.edt_search);
        btn_filter = (RelativeLayout) view.findViewById(R.id.btn_search_filter);
        categoryList = new ArrayList<Category>();
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        category_recycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        category_recycler.setAdapter(categoryAdapter);
    }

    public void getAllCategory(){
        viewModel.getAllCategory().observe(getViewLifecycleOwner(), res ->{
            if (res != null){
                if (res.size() > 0){
                    int oldCount = categoryList.size();

                    categoryList.addAll(res);
                    categoryAdapter.notifyItemRangeInserted(oldCount,categoryList.size());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btn_search_filter:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.category_see_all:
                Intent intent2 = new Intent(getActivity(),CatagoryActivity.class);
                startActivity(intent2);
                break;
            case R.id.chef_see_all:
                Intent intent3 = new Intent(getActivity(), PopularChefsActivity.class);
                startActivity(intent3);
                break;
            case R.id.new_see_all:
                Intent intent4 = new Intent(getActivity(), NewRecipeActivity.class);
                startActivity(intent4);
                break;
            case R.id.trending_see_all:
                Intent intent5 = new Intent(getActivity(), TrendingRecipeActivity.class);
                startActivity(intent5);
                break;
        }
    }
}