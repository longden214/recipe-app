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

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewRecipeViewHolder> {
    Context context;
    List<Recipe> NewRecipeList;
    private RecipeDetailListener recipeDetailListener;


    public NewAdapter(Context context, List<Recipe> NewRecipeList, RecipeDetailListener recipeDetailListener) {
        this.context = context;
        this.NewRecipeList= NewRecipeList;
        this.recipeDetailListener = recipeDetailListener;
    }

    @NonNull
    @Override
    public NewRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_new,parent,false);

        return new NewAdapter.NewRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewRecipeViewHolder holder, int position) {
        Recipe newRecipe = NewRecipeList.get(position);
        if (newRecipe == null){
            return;
        }

        setImageURL(holder.NewRecipeImg, newRecipe.getImage());
        holder.NewRecipeName.setText(newRecipe.getName());
        holder.NewRecipeAuthor.setText("by "+newRecipe.getAuthor());
        holder.itemView.setOnClickListener(view ->{
            recipeDetailListener.onRecipeDetailListener(newRecipe.getId(),newRecipe.getName());
        });
    }

    @Override
    public int getItemCount() {
        return NewRecipeList.size();
    }

    public static final class NewRecipeViewHolder extends RecyclerView.ViewHolder{

        ImageView NewRecipeImg;
        TextView NewRecipeName, NewRecipeAuthor;

        public NewRecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            NewRecipeImg = itemView.findViewById(R.id.imageView2);
            NewRecipeName = itemView.findViewById(R.id.textView9);
            NewRecipeAuthor = itemView.findViewById(R.id.recipe_chef);
        }
    }
}
