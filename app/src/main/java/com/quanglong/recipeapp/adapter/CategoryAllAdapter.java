package com.quanglong.recipeapp.adapter;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.model.Category;

import java.util.ArrayList;

public class CategoryAllAdapter extends RecyclerView.Adapter<CategoryAllAdapter.MyViewHolder> {
    ArrayList<Category> mlist;
    Context mContext;
    private CategoryListener categoryListener;

    public
    CategoryAllAdapter(ArrayList<Category> _list, Context _mContext, CategoryListener _categoryListener){
        this.mlist = _list;
        this.mContext = _mContext;
        this.categoryListener = _categoryListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_categories,parent,false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = mlist.get(position);
        if(category == null){
            return;
        }
        holder.name.setText(mlist.get(position).getName());
        setImageURL(holder.image, mlist.get(position).getImage());
        holder.recipe.setText(Integer.toString(mlist.get(position).getTotalRecipes()));
        holder.itemView.setOnClickListener(view ->{
            categoryListener.onCategoryClicked(category.getId(),category.getName());
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        TextView recipe;

        public MyViewHolder(View v){
            super(v);
            this.name = v.findViewById(R.id.tvname);
            this.image = v.findViewById(R.id.ivImage);
            this.recipe=v.findViewById(R.id.txtrecipe);
        }
    }
}
