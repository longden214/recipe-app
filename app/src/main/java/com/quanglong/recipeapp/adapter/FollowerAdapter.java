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
import com.quanglong.recipeapp.model.FollowRequest;
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.utilities.FCMSend;
import com.quanglong.recipeapp.viewmodels.FollowerViewModel;

import java.util.ArrayList;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder> {
    ArrayList<PopularChef> mlist;
    Context mContext;
    private LifecycleOwner lifecycleOwner;
    private FollowerViewModel followerViewModel;
    private SharedPreferences userlocaldata;


    public FollowerAdapter(ArrayList<PopularChef> _list,Context _mContext,LifecycleOwner lifecycleOwner){
        this.mContext = _mContext;
        this.mlist = _list;
        this.lifecycleOwner = lifecycleOwner;
    }
    @NonNull
    @Override
    public FollowerAdapter.FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_user,parent,false);
        followerViewModel = new FollowerViewModel();
        this.userlocaldata = v.getContext().getSharedPreferences("userDetails",0);
        return new FollowerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerAdapter.FollowerViewHolder holder, int position) {
            PopularChef popularChef = mlist.get(position);
            if(popularChef == null){
                return;
            }

            if (!mlist.get(position).getAvatar().equals("")){
                setImageURL(holder.image, mlist.get(position).getAvatar());
            }else{
                holder.image.setImageResource(R.drawable.avater_default);
            }

            holder.name.setText(mlist.get(position).getUserName());
            holder.recipe.setText(Integer.toString(popularChef.getTotalRecipe()));

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
                                    notifyItemChanged(holder.getAdapterPosition());
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
                    followerViewModel.saveFollow(followRequest).observe(lifecycleOwner, new Observer<RecipeAddResponse>() {
                        @Override
                        public void onChanged(RecipeAddResponse recipeAddResponse) {
                            if (recipeAddResponse.getMessage().equals("Success!")){
                                mlist.get(holder.getAdapterPosition()).setFollowerUser(mlist.get(holder.getAdapterPosition()).isFollowerUser() == false ? true: false);
                                notifyItemChanged(holder.getAdapterPosition());

                                if (recipeAddResponse.getNotificationModels().size() > 0){
                                    for (Notifications item : recipeAddResponse.getNotificationModels()) {
                                        if (item.getListTokenDevice().size() > 0){
                                            for (String itemToken: item.getListTokenDevice()) {
                                                FCMSend.pushNotification(
                                                        mContext,
                                                        itemToken,
                                                        item.getNotificationType(),
                                                        item.getDescription()
                                                );
                                            }
                                        }
                                    }
                                }
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

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class FollowerViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        TextView recipe;
        Button btn_follow;

        public FollowerViewHolder(View v){
            super(v);
            this.image = v.findViewById(R.id.profile_image);
            this.name = v.findViewById(R.id.recipe_chef_name);
            this.recipe=v.findViewById(R.id.location_name);
            this.btn_follow=v.findViewById(R.id.btn_follow);
        }
    }
}
