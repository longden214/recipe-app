package com.quanglong.recipeapp.adapter;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.EditRecipeActivity;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.listener.RecipeListener;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.utilities.UserLocalStore;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.List;

public class RecipeProfileAdptar extends RecyclerView.Adapter<RecipeProfileAdptar.RecipeSaveViewHolder> {
    Context context;
    List<Recipe> RecipeSaveList;
    private RecipeDetailListener recipeDetailListener;
    private RecipeViewModel recipeViewModel;
    private SharedPreferences userLocalDatabase;;
    private LifecycleOwner lifecycleOwner;

    public RecipeProfileAdptar(Context context, List<Recipe> RecipeSaveList,LifecycleOwner _lifecycleOwner, RecipeDetailListener recipeDetailListener){
        this.context=context;
        this.RecipeSaveList=RecipeSaveList;
        this.recipeDetailListener = recipeDetailListener;
        this.lifecycleOwner = _lifecycleOwner;
    }

    @NonNull
    @Override
    public RecipeSaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_my_profile,parent,false);
        recipeViewModel = new RecipeViewModel();
        userLocalDatabase = view.getContext().getSharedPreferences("userDetails", 0);

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
        holder.RecipeReting.setText(Float.toString(newRecipe.getAvgRating()));

        holder.recipe_option.setOnClickListener(view -> {
            // Initializing the popup menu and giving the reference as current context
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.recipe_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    String menuSelected = menuItem.toString();
                    // Toast message on menu item clicked
                    int id = newRecipe.getId();
                    if (menuSelected.equals("Remove")){
                        int user_id = userLocalDatabase.getInt("id", -1);
                        recipeViewModel.recipeDelete(id,user_id).observe(lifecycleOwner, new Observer<String>() {
                            @Override
                            public void onChanged(String res) {
                                if (res != null) {
                                    if (res.equals("Success!")) {
                                        RecipeSaveList.remove(holder.getAdapterPosition());
                                        notifyItemRemoved(holder.getAdapterPosition());
                                        notifyItemRangeChanged(holder.getAdapterPosition(),RecipeSaveList.size());
                                    }else{
                                        Toast.makeText(view.getContext(), res, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }else if(menuSelected.equals("Edit")){
                        Intent intent = new Intent(view.getContext(), EditRecipeActivity.class);
                        intent.putExtra("id",id);
                        context.startActivity(intent);
                    }
                    return true;
                }
            });
            // Showing the popup menu
            popupMenu.show();
        });

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
        CardView recipe_option;

        public RecipeSaveViewHolder(@NonNull View itemView) {
            super(itemView);

            RecipeImg = itemView.findViewById(R.id.imageView2);
            RecipeName = itemView.findViewById(R.id.tv_recipe_name);
            RecipeTime = itemView.findViewById(R.id.textView13);
            RecipeReting = itemView.findViewById(R.id.textView12);
            recipe_option = itemView.findViewById(R.id.recipe_option);
        }
    }
}
