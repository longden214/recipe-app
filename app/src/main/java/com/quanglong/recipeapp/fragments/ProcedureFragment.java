package com.quanglong.recipeapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.ProcedureDetailAdapter;
import com.quanglong.recipeapp.model.Step;
import com.quanglong.recipeapp.responses.RecipeDetailResponse;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProcedureFragment extends Fragment {
    int id;
    private SharedPreferences userLocalDatabase;
    private RecipeViewModel recipeViewModel;
    private List<Step> listSteps = new ArrayList<>();
    private ProcedureDetailAdapter adapter;
    private RecyclerView recyclerView;
    private TextView stepTotal;

    public ProcedureFragment(int _id) {
        // Required empty public constructor
        id = _id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_procedure, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);
        this.recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.step_rv);
        stepTotal = (TextView) view.findViewById(R.id.step_total_item);
        setStepRecycler(listSteps);
        getRecipeDetail();
    }

    private void getRecipeDetail() {
        recipeViewModel.getRecipeDetailWithParam(id, userLocalDatabase.getInt("id", -1)).observe(getViewLifecycleOwner(), new Observer<RecipeDetailResponse>() {
            @Override
            public void onChanged(RecipeDetailResponse recipeDetailResponse) {
                if (recipeDetailResponse != null) {
                    if (recipeDetailResponse.getRecipeDetail() != null) {
                        int oldCount = listSteps.size();
                        listSteps.addAll(recipeDetailResponse.getListSteps());
                        adapter.notifyItemRangeInserted(oldCount, listSteps.size());
                        stepTotal.setText(listSteps.size() + " items");
                    }
                }
            }
        });
    }

    private void setStepRecycler(List<Step> mlistSteps) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapter = new ProcedureDetailAdapter(getActivity(), mlistSteps);
        recyclerView.setAdapter(adapter);
    }
}