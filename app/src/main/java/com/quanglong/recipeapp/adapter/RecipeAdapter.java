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
import com.quanglong.recipeapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    Context mContext;
    ArrayList<Recipe> mlist;


    public RecipeAdapter(ArrayList<Recipe> _list, Context _mContext) {
        this.mlist = _list;
        this.mContext = _mContext;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_recipe,parent,false);
        RecipeViewHolder mvh = new RecipeViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mlist.get(position);
        if (recipe == null){
            return;
        }

        setImageURL(holder.RecipeImg, recipe.getImage());
        holder.RecipeName.setText(recipe.getName());
        holder.RecipeAuthor.setText("by " +recipe.getAuthor());
        holder.RecipeTime.setText(Integer.toString(recipe.getTotalViews())+ " Min");
        holder.RecipeRating.setText(Integer.toString(recipe.getTotalRating()));
//        setImageURL(holder.RecipeImgAvatar, recipe.getAuthorAvatar());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static final class RecipeViewHolder extends RecyclerView.ViewHolder{

        ImageView RecipeImg,RecipeImgAvatar;
        TextView RecipeName, RecipeAuthor,RecipeTime, RecipeRating;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            RecipeImg = itemView.findViewById(R.id.imageView2);
            RecipeName = itemView.findViewById(R.id.textView9);
            RecipeAuthor = itemView.findViewById(R.id.recipe_chef);
            RecipeTime = itemView.findViewById(R.id.textView13);
            RecipeRating = itemView.findViewById(R.id.textView12);
//            RecipeImgAvatar = itemView.findViewById(R.id.profile_image);
        }
    }
}
