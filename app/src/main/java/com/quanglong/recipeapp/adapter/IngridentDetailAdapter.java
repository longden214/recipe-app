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
import com.quanglong.recipeapp.listener.CategoryListener;
import com.quanglong.recipeapp.model.Category;
import com.quanglong.recipeapp.model.Ingredient;

import java.util.List;

public class IngridentDetailAdapter extends RecyclerView.Adapter<IngridentDetailAdapter.IngridentViewHolder> {

    Context context;
    List<Ingredient> ingredientList;

    public IngridentDetailAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngridentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingrident,parent,false);

        return new IngridentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngridentViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        if (ingredient == null){
            return;
        }

        holder.ingredient_name.setText(ingredient.getName());
        holder.ingredient_unit.setText(ingredient.getQuantity());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static final class IngridentViewHolder extends RecyclerView.ViewHolder{

        TextView ingredient_name, ingredient_unit;

        public IngridentViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_unit = itemView.findViewById(R.id.ingredient_unit);
        }
    }
}
