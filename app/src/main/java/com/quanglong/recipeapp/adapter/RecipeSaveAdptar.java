package com.quanglong.recipeapp.adapter;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.model.SaveRecipeRequest;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.List;

public class RecipeSaveAdptar extends RecyclerView.Adapter<RecipeSaveAdptar.RecipeSaveViewHolder> {
    Context context;
    List<Recipe> RecipeSaveList;
    private RecipeDetailListener recipeDetailListener;
    private LifecycleOwner lifecycleOwner;
    private RecipeViewModel recipeViewModel;
    private SharedPreferences userlocalData;

    public RecipeSaveAdptar(Context context,List<Recipe> RecipeSaveList,RecipeDetailListener recipeDetailListener,LifecycleOwner lifecycleOwner){
        this.context=context;
        this.RecipeSaveList=RecipeSaveList;
        this.recipeDetailListener = recipeDetailListener;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public RecipeSaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_save,parent,false);
        recipeViewModel = new RecipeViewModel();
        this.userlocalData = view.getContext().getSharedPreferences("userDetails",0);
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
        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btn_save.equals(R.drawable.ic_saved)){
                    recipeViewModel.unRecipe(newRecipe.getId(),userlocalData.getInt("id",-1)).observe(lifecycleOwner, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s != null){
                                if(s.equals("Success!")){
                                    RecipeSaveList.get(holder.getAdapterPosition()).setSaveRecipe(RecipeSaveList.get(holder.getAdapterPosition()).isSaveRecipe() == true?false:true);
                                    notifyItemChanged(holder.getAdapterPosition());
                                }else {
                                    if(s.equals("Failed!")){
                                        Toast.makeText(v.getContext(), "Save failed!", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(v.getContext(), s, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }else {
                    SaveRecipeRequest saveRecipeRequest = new SaveRecipeRequest(newRecipe.getId(),userlocalData.getInt("id",-1));
                    recipeViewModel.saveRecipe(saveRecipeRequest).observe(lifecycleOwner, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("Success!")){
                                RecipeSaveList.get(holder.getAdapterPosition()).setSaveRecipe(RecipeSaveList.get(holder.getAdapterPosition()).isSaveRecipe() == true?false:true);
                                notifyItemChanged(holder.getAdapterPosition());
                            }else {
                                if(s.equals("Failed!")){
                                    Toast.makeText(v.getContext(), "Save failed!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(v.getContext(), s, Toast.LENGTH_LONG).show();
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
