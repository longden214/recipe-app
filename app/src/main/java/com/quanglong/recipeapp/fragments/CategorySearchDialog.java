package com.quanglong.recipeapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.CategorySearchAdapter;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.responses.CategoryResponse;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;

import java.util.ArrayList;

public class CategorySearchDialog extends DialogFragment implements View.OnClickListener, CategoryListener {
    private RecyclerView recyclerView;
    private ArrayList<Category> mlistCategory = new ArrayList<>();
    private CategoryViewModel viewModel;
    private CategorySearchAdapter adapter;

    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private ProgressBar progressBar_loading;
    private ProgressBar progressBar_more;
    private boolean isLoading = false;
    private boolean isLoadingMore = false;

    private CategorySearchDialog.Callback callback;

    public static CategorySearchDialog newInstance() {
        return new CategorySearchDialog();
    }

    public void setCallback(CategorySearchDialog.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_category, container, false);
        TextView close = view.findViewById(R.id.btn_cancel);
        EditText edt_search = view.findViewById(R.id.category_search);
        progressBar_loading = view.findViewById(R.id.progressBar_loading);
        progressBar_more = view.findViewById(R.id.progressBar_more);
        recyclerView = (RecyclerView) view.findViewById(R.id.category_rv);
        viewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        setCategoryRecycler(mlistCategory);
        getCategoryAll("");
        close.setOnClickListener(this);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                adapter.notifyItemRangeRemoved(0, mlistCategory.size());
                mlistCategory.clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_search.getText().toString().length() > 0) {
                    edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_close_24, 0);
                    edt_search.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            final int DRAWABLE_LEFT = 0;
                            final int DRAWABLE_TOP = 1;
                            final int DRAWABLE_RIGHT = 2;
                            final int DRAWABLE_BOTTOM = 3;

                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                if (event.getRawX() >= (edt_search.getRight() -
                                        edt_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                    edt_search.setText("");

                                    edt_search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_normal, 0, 0, 0);
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                } else
                    edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
                    currentPage = 1;
                    getCategoryAll(edt_search.getText().toString());
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    if (currentPage < totalAvailablePages) {
                        currentPage += 1;
                        getCategoryAll(edt_search.getText().toString());
                    }
                }
            }
        });

        return view;
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

    private void getCategoryAll(String search) {
        toggleLoading();
        viewModel.getCategoryWithParam(search, false, true, false,
                true, currentPage, 20).observe(this, new Observer<CategoryResponse>() {
            @Override
            public void onChanged(CategoryResponse categories) {
                if (categories != null) {
                    totalAvailablePages = categories.getTotalPage();
                    if (currentPage == 1) {
                        isLoading = true;
                    } else {
                        isLoadingMore = true;
                    }
                    toggleLoading();
                    if (categories.getCaregoties().size() > 0) {
                        int oldCount = mlistCategory.size();

                        mlistCategory.addAll(categories.getCaregoties());
                        adapter.notifyItemRangeInserted(oldCount, mlistCategory.size());
                    }
                }
            }
        });
    }

    private void setCategoryRecycler(ArrayList<Category> mlistCategory) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new CategorySearchAdapter(getActivity(), mlistCategory, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onCategoryClicked(int cate_id, String cate_name) {
        callback.onActionClick(cate_id, cate_name);
        dismiss();
    }

    public interface Callback {
        void onActionClick(int id,String name);
    }
}
