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
import com.quanglong.recipeapp.model.PopularChef;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.SaveRecipeRequest;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    Context mContext;
    ArrayList<Recipe> mlist;
    private RecipeDetailListener recipeDetailListener;
    private LifecycleOwner lifecycleOwner;
    private RecipeViewModel recipeViewModel;
    private SharedPreferences userlocalData;


    public RecipeAdapter(ArrayList<Recipe> _list, Context _mContext,RecipeDetailListener recipeDetailListener,LifecycleOwner lifecycleOwner) {
        this.mlist = _list;
        this.mContext = _mContext;
        this.recipeDetailListener = recipeDetailListener;
        this.lifecycleOwner =lifecycleOwner;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_recipe,parent,false);
        RecipeViewHolder mvh = new RecipeViewHolder(v);
        recipeViewModel = new RecipeViewModel();
        this.userlocalData = v.getContext().getSharedPreferences("userDetails",0);
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
        holder.RecipeTime.setText(recipe.getCookTime()+ " Mins");
        holder.RecipeRating.setText(Float.toString(recipe.getAvgRating()));

        if (recipe.isSaveRecipe()){
            holder.btn_save.setImageResource(R.drawable.ic_saved);
        }else{
            holder.btn_save.setImageResource(R.drawable.ic_save);
        }

        holder.itemView.setOnClickListener(view ->{
            recipeDetailListener.onRecipeDetailListener(recipe.getId(),recipe.getName());
        });
        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btn_save.equals(R.drawable.ic_saved)){
                    recipeViewModel.unRecipe(recipe.getId(),userlocalData.getInt("id",-1)).observe(lifecycleOwner, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s != null){
                                if(s.equals("Success!")){
                                    mlist.get(holder.getAdapterPosition()).setSaveRecipe(mlist.get(holder.getAdapterPosition()).isSaveRecipe() == true?false:true);
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
                    SaveRecipeRequest saveRecipeRequest = new SaveRecipeRequest(recipe.getId(),userlocalData.getInt("id",-1));
                    recipeViewModel.saveRecipe(saveRecipeRequest).observe(lifecycleOwner, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("Success!")){
                                mlist.get(holder.getAdapterPosition()).setSaveRecipe(mlist.get(holder.getAdapterPosition()).isSaveRecipe() == true?false:true);
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
//        setImageURL(holder.RecipeImgAvatar, recipe.getAuthorAvatar());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static final class RecipeViewHolder extends RecyclerView.ViewHolder{

        ImageView RecipeImg,RecipeImgAvatar, btn_save;
        TextView RecipeName, RecipeAuthor,RecipeTime, RecipeRating;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_save = itemView.findViewById(R.id.btn_save);
            RecipeImg = itemView.findViewById(R.id.imageView2);
            RecipeName = itemView.findViewById(R.id.textView9);
            RecipeAuthor = itemView.findViewById(R.id.recipe_chef);
            RecipeTime = itemView.findViewById(R.id.textView13);
            RecipeRating = itemView.findViewById(R.id.textView12);
//            RecipeImgAvatar = itemView.findViewById(R.id.profile_image);
        }
    }
}
