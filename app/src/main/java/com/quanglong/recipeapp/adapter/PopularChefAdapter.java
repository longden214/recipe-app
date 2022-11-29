package com.quanglong.recipeapp.adapter;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.listener.UserListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.viewmodels.FollowerViewModel;

import java.util.ArrayList;
import java.util.List;

public class PopularChefAdapter extends RecyclerView.Adapter<PopularChefAdapter.PopularChefViewHolder> {
    Context mContext;
    ArrayList<PopularChef> mlist;
    private UserListener userListener;
    private LifecycleOwner lifecycleOwner;
    private FollowerViewModel followerViewModel;
    private SharedPreferences userlocaldata;

    public PopularChefAdapter(ArrayList<PopularChef> _list, Context _mContext,UserListener userListener,LifecycleOwner lifecycleOwner) {
        this.mlist = _list;
        this.mContext = _mContext;
        this.userListener = userListener;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public PopularChefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false);
        PopularChefViewHolder mvh = new PopularChefViewHolder(v);
        followerViewModel = new FollowerViewModel();
        this.userlocaldata = v.getContext().getSharedPreferences("userDetails",0);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularChefViewHolder holder, int position) {
        PopularChef popularChef = mlist.get(position);
        if (popularChef == null){
            return;
        }

        if (!popularChef.getAvatar().equals("")){
            setImageURL(holder.PopularChefImg, popularChef.getAvatar());
        }else{
            holder.PopularChefImg.setImageResource(R.drawable.avater_default);
        }

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
        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btn_follow.getText().toString().equals("Following")){
                    followerViewModel.unFollow(userlocaldata.getInt("id",-1),popularChef.getId()).observe(lifecycleOwner, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s != null) {
                                if (s.equals("Success!")){
                                    mlist.get(holder.getAdapterPosition()).setFollowerUser(mlist.get(holder.getAdapterPosition()).isFollowerUser() == true ? false: true);
                                    notifyItemRangeChanged(holder.getAdapterPosition(),mlist.size());
                                }else{
                                    if (s.equals("Failed!")){
                                        Toast.makeText(v.getContext(), "Follow failed!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(v.getContext(), s, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }else{
                    FollowRequest followRequest = new FollowRequest(userlocaldata.getInt("id",-1),popularChef.getId());
                    followerViewModel.saveFollow(followRequest).observe(lifecycleOwner, new Observer< RecipeAddResponse>() {
                        @Override
                        public void onChanged(RecipeAddResponse recipeAddResponse) {
                            if (recipeAddResponse.getMessage().equals("Success!")){
                                mlist.get(holder.getAdapterPosition()).setFollowerUser(mlist.get(holder.getAdapterPosition()).isFollowerUser() == false ? true: false);
                                notifyItemRangeChanged(holder.getAdapterPosition(),mlist.size());
                            }else{
                                if (recipeAddResponse.getMessage().equals("Failed!")){
                                    Toast.makeText(v.getContext(), "Follow failed!", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(v.getContext(), recipeAddResponse.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }
            }
        });
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
