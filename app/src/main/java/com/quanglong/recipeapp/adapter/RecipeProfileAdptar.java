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
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.Recipe;

import java.util.List;

public class RecipeProfileAdptar extends RecyclerView.Adapter<RecipeProfileAdptar.RecipeSaveViewHolder> {
    Context context;
    List<Recipe> RecipeSaveList;
    private RecipeDetailListener recipeDetailListener;

    public RecipeProfileAdptar(Context context, List<Recipe> RecipeSaveList, RecipeDetailListener recipeDetailListener){
        this.context=context;
        this.RecipeSaveList=RecipeSaveList;
        this.recipeDetailListener = recipeDetailListener;
    }

    @NonNull
    @Override
    public RecipeSaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_my_profile,parent,false);

        return new RecipeSaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeProfileAdptar.RecipeSaveViewHolder holder, int position) {
        Recipe newRecipe = RecipeSaveList.get(position);
        if (newRecipe == null){
            return;
        }

        setImageURL(holder.RecipeImg, newRecipe.getImage());
        holder.RecipeName.setText(newRecipe.getName());
        holder.RecipeTime.setText((newRecipe.getCookTime()+ " mins"));
        holder.RecipeReting.setText(Integer.toString(newRecipe.getTotalRating()));
        holder.itemView.setOnClickListener(view ->{
            recipeDetailListener.onRecipeDetailListener(newRecipe.getId(),newRecipe.getName());
        });
    }

    @Override
    public int getItemCount() {
        return RecipeSaveList.size();
    }

    public static final class RecipeSaveViewHolder extends RecyclerView.ViewHolder{

        ImageView RecipeImg;
        TextView RecipeName,RecipeTime,RecipeReting;

        public RecipeSaveViewHolder(@NonNull View itemView) {
            super(itemView);

            RecipeImg = itemView.findViewById(R.id.imageView2);
            RecipeName = itemView.findViewById(R.id.tv_recipe_name);
            RecipeTime = itemView.findViewById(R.id.textView13);
            RecipeReting = itemView.findViewById(R.id.textView12);
        }
    }
}
