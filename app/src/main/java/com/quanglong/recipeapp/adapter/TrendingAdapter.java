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

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {
    Context context;
    List<Recipe> TrendingList;
    private RecipeDetailListener recipeDetailListener;


    public TrendingAdapter(Context context, List<Recipe> TrendingList,RecipeDetailListener recipeDetailListener) {
        this.context = context;
        this.TrendingList= TrendingList;
        this.recipeDetailListener = recipeDetailListener;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_trending,parent,false);

        return new TrendingAdapter.TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position) {
        Recipe trendingRecipe = TrendingList.get(position);
        if (trendingRecipe == null){
            return;
        }

        setImageURL(holder.TrendingImg, trendingRecipe.getImage());
        holder.TrendingName.setText(trendingRecipe.getName());
        holder.TrendingAuthor.setText("by " +trendingRecipe.getAuthor());
        holder.TrendingCategory.setText(trendingRecipe.getCategoryDisplay());
        holder.TrendingTime.setText(trendingRecipe.getCookTime()+ " Mins");
        holder.TrendingRating.setText(Float.toString(trendingRecipe.getAvgRating()));

        if (!trendingRecipe.getAuthorAvatar().equals("")){
            setImageURL(holder.TrendingImgAvatar, trendingRecipe.getAuthorAvatar());
        }else{
            holder.TrendingImgAvatar.setImageResource(R.drawable.avater_default);
        }

        if (trendingRecipe.isSaveRecipe()){
            holder.btn_save.setImageResource(R.drawable.ic_saved);
        }else{
            holder.btn_save.setImageResource(R.drawable.ic_save);
        }

        holder.itemView.setOnClickListener(view ->{
            recipeDetailListener.onRecipeDetailListener(trendingRecipe.getId(),trendingRecipe.getName());
        });

    }

    @Override
    public int getItemCount() {
        return TrendingList.size();
    }

    public static final class TrendingViewHolder extends RecyclerView.ViewHolder{

        ImageView TrendingImg,TrendingImgAvatar, btn_save;
        TextView TrendingName, TrendingAuthor,TrendingTime, TrendingRating,TrendingCategory;

        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_save = itemView.findViewById(R.id.btn_save);
            TrendingImg = itemView.findViewById(R.id.imageView2);
            TrendingName = itemView.findViewById(R.id.recipe_name);
            TrendingAuthor = itemView.findViewById(R.id.recipe_chef);
            TrendingTime = itemView.findViewById(R.id.textView13);
            TrendingRating = itemView.findViewById(R.id.textView12);
            TrendingCategory = itemView.findViewById(R.id.category_name);
            TrendingImgAvatar = itemView.findViewById(R.id.profile_image);
        }
    }
}
