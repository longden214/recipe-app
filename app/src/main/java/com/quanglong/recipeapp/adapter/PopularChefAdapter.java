package com.quanglong.recipeapp.adapter;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.listener.UserListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.PopularChef;

import java.util.ArrayList;
import java.util.List;

public class PopularChefAdapter extends RecyclerView.Adapter<PopularChefAdapter.PopularChefViewHolder> {
    Context mContext;
    ArrayList<PopularChef> mlist;
    private UserListener userListener;

    public PopularChefAdapter(ArrayList<PopularChef> _list, Context _mContext,UserListener userListener) {
        this.mlist = _list;
        this.mContext = _mContext;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public PopularChefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false);
        PopularChefViewHolder mvh = new PopularChefViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularChefViewHolder holder, int position) {
        PopularChef popularChef = mlist.get(position);
        if (popularChef == null){
            return;
        }

        setImageURL(holder.PopularChefImg, popularChef.getAvatar());
        holder.PopularChefName.setText(popularChef.getUserName());
        holder.totalRecipes.setText(Integer.toString(popularChef.getTotalRecipe()));

        if (popularChef.isFollowerUser()){
            holder.btn_follow.setText("Following");
            holder.btn_follow.setBackgroundResource(R.drawable.bg_following);
            holder.btn_follow.setTextColor(Color.parseColor("#121212"));
        }else{
            holder.btn_follow.setText("Follow");
            holder.btn_follow.setBackgroundResource(R.drawable.bg_button_follow);
            holder.btn_follow.setTextColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(view ->{
            userListener.onUserClicked(popularChef);
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static final class PopularChefViewHolder extends RecyclerView.ViewHolder{

        ImageView PopularChefImg;
        TextView PopularChefName, totalRecipes;
        Button btn_follow;

        public PopularChefViewHolder(@NonNull View itemView) {
            super(itemView);

            PopularChefImg = itemView.findViewById(R.id.profile_image);
            PopularChefName = itemView.findViewById(R.id.recipe_chef_name);
            totalRecipes = itemView.findViewById(R.id.location_name);
            btn_follow = itemView.findViewById(R.id.btn_follow);
        }
    }

}
