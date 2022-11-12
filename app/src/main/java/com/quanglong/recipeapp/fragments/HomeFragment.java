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
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ImageView;

import com.quanglong.recipeapp.R;
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
    ImageView imageView;

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
        imageView.setOnClickListener(this);
        setCategoryRecycler(categoryList);
        getAllCategory();
    }

    private void doInitialization(View view) {
        imageView = view.findViewById(R.id.ic_arrow_right);
        category_recycler = view.findViewById(R.id.category_list);
        btn_filter = (RelativeLayout) view.findViewById(R.id.btn_search_filter);
        categoryList = new ArrayList<Category>();
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
//        category_recycler.setHasFixedSize(true);
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
                FilterBottomSheetFragment bottomSheetFragment = new FilterBottomSheetFragment();
                bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;
            case R.id.ic_arrow_right:
                Intent intent = new Intent(getActivity(),CatagoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}