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

public class RecipeSaveAdptar extends RecyclerView.Adapter<RecipeSaveAdptar.RecipeSaveViewHolder> {
    Context context;
    List<Recipe> RecipeSaveList;
    private RecipeDetailListener recipeDetailListener;

    public RecipeSaveAdptar(Context context,List<Recipe> RecipeSaveList,RecipeDetailListener recipeDetailListener){
        this.context=context;
        this.RecipeSaveList=RecipeSaveList;
        this.recipeDetailListener = recipeDetailListener;
    }

    @NonNull
    @Override
    public RecipeSaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_save,parent,false);

        return new RecipeSaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeSaveAdptar.RecipeSaveViewHolder holder, int position) {
        Recipe newRecipe = RecipeSaveList.get(position);
        if (newRecipe == null){
            return;
        }

        setImageURL(holder.RecipeImg, newRecipe.getImage());
        holder.RecipeName.setText(newRecipe.getName());
        holder.RecipeAuthor.setText("by "+newRecipe.getAuthor());
        holder.RecipeTime.setText((newRecipe.getCookTime()+ "mins"));
        holder.RecipeReting.setText(Float.toString(newRecipe.getAvgRating()));

        if (newRecipe.isSaveRecipe()){
            holder.btn_save.setImageResource(R.drawable.ic_saved);
        }else{
            holder.btn_save.setImageResource(R.drawable.ic_save);
        }

        holder.itemView.setOnClickListener(view ->{
            recipeDetailListener.onRecipeDetailListener(newRecipe.getId(),newRecipe.getName());
        });
    }

    @Override
    public int getItemCount() {
        return RecipeSaveList.size();
    }

    public static final class RecipeSaveViewHolder extends RecyclerView.ViewHolder{

        ImageView RecipeImg,btn_save;
        TextView RecipeName, RecipeAuthor,RecipeTime,RecipeReting;

        public RecipeSaveViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_save = itemView.findViewById(R.id.btn_save);
            RecipeImg = itemView.findViewById(R.id.imageView2);
            RecipeName = itemView.findViewById(R.id.tv_recipe_name);
            RecipeAuthor = itemView.findViewById(R.id.tv_chef_name);
            RecipeTime = itemView.findViewById(R.id.textView13);
            RecipeReting = itemView.findViewById(R.id.textView12);
        }
    }
}
