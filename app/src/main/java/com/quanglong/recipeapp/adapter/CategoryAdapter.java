package com.quanglong.recipeapp.adapter;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> categoryList;
    private CategoryListener categoryListener;

    public CategoryAdapter(Context context, List<Category> categoryList,CategoryListener _categoryListener) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryListener = _categoryListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_category,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null){
            return;
        }

        setImageURL(holder.categoryImg, category.getImage());
        holder.categoryName.setText(category.getName());
        holder.totalRecipes.setText(category.getTotalRecipes() + " Recipes");
        holder.itemView.setOnClickListener(view -> { categoryListener.onCategoryClicked(category.getId(), category.getName()); });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImg;
        TextView categoryName, totalRecipes;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImg = itemView.findViewById(R.id.img_category);
            categoryName = itemView.findViewById(R.id.txt_cate_name);
            totalRecipes = itemView.findViewById(R.id.txt_cate_recipe);
        }
    }
}
