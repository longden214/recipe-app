package com.quanglong.recipeapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.flexbox.JustifyContent;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.responses.CategoryResponse;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

import nl.bryanderidder.themedtogglebuttongroup.ThemedButton;
import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FilterBottomSheetFragment extends BottomSheetDialogFragment {
    private Button btn_filter;
    private CategoryViewModel viewModel;
    private ArrayList<Category> mlistCategory = new ArrayList<>();
    private LinearLayout category_group;
    ThemedToggleButtonGroup themedButtonGroupTime;
    ThemedToggleButtonGroup themedButtonGroupRate;
    ThemedToggleButtonGroup themedButtonGroupCategory;
    private FilterBottomSheetFragment.Callback callback;
    private String time;
    private int star;
    private int[] cateId;

    public FilterBottomSheetFragment(String time, int star, int[] cateId) {
        this.time = time;
        this.star = star;
        this.cateId = cateId;
    }

    public static FilterBottomSheetFragment newInstance() {
        return new FilterBottomSheetFragment(newInstance().time, newInstance().star, newInstance().cateId);
    }

    public void setCallback(FilterBottomSheetFragment.Callback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btn_filter = view.findViewById(R.id.btn_filter);
        this.themedButtonGroupTime = view.findViewById(R.id.time);
        this.themedButtonGroupRate = view.findViewById(R.id.rating);
        this.themedButtonGroupCategory = view.findViewById(R.id.category);
        setSelectedButton();
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        getCategoryAll();

        this.btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ThemedButton> selectedButtonsTime = themedButtonGroupTime.getSelectedButtons();
                List<ThemedButton> selectedButtonsRate = themedButtonGroupRate.getSelectedButtons();
                List<ThemedButton> selectedButtonsCategory = themedButtonGroupCategory.getSelectedButtons();
                int[] category_id;

                if (selectedButtonsCategory.size() > 0){
                    category_id = new int[selectedButtonsCategory.size()];

                    for (int i = 0; i < selectedButtonsCategory.size(); i++) {
                        category_id[i] = Integer.parseInt(selectedButtonsCategory.get(i).getTag().toString());
                    }
                }else{
                    category_id = new int[]{};
                }

                String time_selected = selectedButtonsTime.size() == 0 ? "" : selectedButtonsTime.get(0).getText();
                int rate_selected = selectedButtonsRate.size() == 0 ? 0: Integer.parseInt(selectedButtonsRate.get(0).getTag().toString());

                callback.onFilterClick(
                        time_selected,
                        rate_selected,
                        category_id
                        );
                dismiss();
            }
        });
    }

    private void getCategoryAll() {
        viewModel.getCategoryWithParam("", false, true, false,
                true, 1, 1000).observe(this, new Observer<CategoryResponse>() {
            @Override
            public void onChanged(CategoryResponse categories) {
                if (categories != null) {
                    if (categories.getCaregoties().size() > 0) {
                        mlistCategory.addAll(categories.getCaregoties());
                        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        buttonLayoutParams.setMargins(50, 10, 0, 0);
                        for (Category item : mlistCategory)
                        {
                            ThemedButton btn = new ThemedButton(getContext());
                            btn.setTag(item.getId());
                            btn.isSelected();
                            btn.setText(item.getName());
                            btn.setBgColor(getResources().getColor(R.color.white));
                            btn.setBorderWidth(8);
                            btn.setIgnoreGravity(Gravity.END);
                            btn.setTextColor(getResources().getColor(R.color.toggle_btn));
                            btn.setBorderColor(getResources().getColor(R.color.toggle_btn));
                            btn.setSelectedBorderColor(getResources().getColor(R.color.toggle_btn));
                            btn.setSelectedBgColor(getResources().getColor(R.color.toggle_btn));
                            btn.setLayoutParams(buttonLayoutParams);
                            themedButtonGroupCategory.addView(btn,
                                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,130)
                            );
                        }
                    }
                }
            }
        });
    }

    public interface Callback {
        void onFilterClick(String time,int star,int[] cateId);
    }

    private void setSelectedButton(){
        List<ThemedButton> buttonsTime = themedButtonGroupTime.getButtons();
        List<ThemedButton> buttonsRate = themedButtonGroupRate.getButtons();
        List<ThemedButton> buttonsCategory = themedButtonGroupCategory.getButtons();

        for (int i = 0; i < buttonsTime.size(); i++) {
            if (time.equals(buttonsTime.get(i).getTag().toString())){
                buttonsTime.get(i).isSelected();
            }
        }

        for (int i = 0; i < buttonsRate.size(); i++) {
            if (star == Integer.parseInt(buttonsRate.get(i).getTag().toString())){
                buttonsRate.get(i).setSelected(true);
            }
        }

        for (int i = 0; i < buttonsCategory.size(); i++) {
            for (int j = 0; j < cateId.length; j++) {
                if (cateId[j] == Integer.parseInt(buttonsCategory.get(i).getTag().toString())){
                    buttonsCategory.get(i).setSelected(true);
                }
            }
        }

    }
}