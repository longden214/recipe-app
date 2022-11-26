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

import java.util.ArrayList;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder> {
    ArrayList<PopularChef> mlist;
    Context mContext;

    public FollowerAdapter(ArrayList<PopularChef> _list,Context _mContext){
        this.mContext = _mContext;
        this.mlist = _list;
    }
    @NonNull
    @Override
    public FollowerAdapter.FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false);
        return new FollowerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerAdapter.FollowerViewHolder holder, int position) {
            PopularChef popularChef = mlist.get(position);
            if(popularChef == null){
                return;
            }
        setImageURL(holder.image, mlist.get(position).getAvatar());
            holder.name.setText(mlist.get(position).getUserName());
            holder.recipe.setText(Integer.toString(popularChef.getTotalRecipe()));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class FollowerViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        TextView recipe;

        public FollowerViewHolder(View v){
            super(v);
            this.image = v.findViewById(R.id.profile_image);
            this.name = v.findViewById(R.id.recipe_chef_name);
            this.recipe=v.findViewById(R.id.location_name);
        }
    }
}
