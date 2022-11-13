package com.quanglong.recipeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.databinding.ItemAddIngredientBinding;
import com.quanglong.recipeapp.listener.IngredientListener;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.R;

import java.util.List;

public class IngredientAddAdapter extends RecyclerView.Adapter<IngredientAddAdapter.IngredientAddViewHolder> {
    private List<Ingredient> ingredients;
    private LayoutInflater layoutInflater;
    private IngredientListener ingredientListener;

    public IngredientAddAdapter(List<Ingredient> _ingredients, IngredientListener _ingredientListener){
        this.ingredients = _ingredients;
        this.ingredientListener = _ingredientListener;
    }

    @NonNull
    @Override
    public IngredientAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemAddIngredientBinding ingredientBindingBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_add_ingredient,parent, false
        );

        return new IngredientAddViewHolder(ingredientBindingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAddViewHolder holder, int position) {
        holder.bindTVShow(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientAddViewHolder extends RecyclerView.ViewHolder{
        private ItemAddIngredientBinding itemAddIngredientBinding;
        private IngredientAddAdapter adapter;

        public IngredientAddViewHolder(ItemAddIngredientBinding itemAddIngredientBinding) {
            super(itemAddIngredientBinding.getRoot());
            this.itemAddIngredientBinding = itemAddIngredientBinding;
        }

        public void bindTVShow(Ingredient ingredient){
            itemAddIngredientBinding.setIngredient(ingredient);
            itemAddIngredientBinding.executePendingBindings();
            itemAddIngredientBinding.btnRemoveIngredient.setOnClickListener(view -> ingredientListener.onIngredientRemove(getAdapterPosition()));
        }
    }

}
