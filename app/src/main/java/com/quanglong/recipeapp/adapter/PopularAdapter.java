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
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.model.User;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularChefViewHolder> {
    Context context;
    List<PopularChef> PopularChefList;

    public PopularAdapter(Context context, List<PopularChef> PopularChefList) {
        this.context = context;
        this.PopularChefList = PopularChefList;
    }

    @NonNull
    @Override
    public PopularChefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_chef,parent,false);

        return new PopularAdapter.PopularChefViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularChefViewHolder holder, int position) {
        PopularChef popularChef = PopularChefList.get(position);
        if (popularChef == null){
            return;
        }

        setImageURL(holder.PopularChefImg, popularChef.getAvatar());
        holder.PopularChefName.setText(popularChef.getUserName());
        holder.totalRecipes.setText(Integer.toString(popularChef.getTotalRecipe()));
    }

    @Override
    public int getItemCount() {
        return PopularChefList.size();
    }

    public static final class PopularChefViewHolder extends RecyclerView.ViewHolder{

        ImageView PopularChefImg;
        TextView PopularChefName, totalRecipes;

        public PopularChefViewHolder(@NonNull View itemView) {
            super(itemView);

            PopularChefImg = itemView.findViewById(R.id.profile_image);
            PopularChefName = itemView.findViewById(R.id.textuser);
            totalRecipes = itemView.findViewById(R.id.txt_recipe_number);
        }
    }

}
