package com.quanglong.recipeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.IngredientAddAdapter;
import com.quanglong.recipeapp.databinding.ActivityAddRecipeBinding;
import com.quanglong.recipeapp.fragments.CategorySearchDialog;
import com.quanglong.recipeapp.fragments.EditProfileDialog;
import com.quanglong.recipeapp.listener.IngredientListener;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity implements View.OnClickListener, IngredientListener {
    private Toolbar toolbar;
    private ActivityAddRecipeBinding activityAddRecipeBinding;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private IngredientAddAdapter ingredientAddAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        StatusBarConfig.StatusBarCustom(this);

        doInitialization();
        customActionBar();
        setIngredientAddAdapter();
        setListener();
    }

    private void setListener() {
        this.activityAddRecipeBinding.btnCancel.setOnClickListener((View.OnClickListener) this);
        this.activityAddRecipeBinding.btnAddIngredient.setOnClickListener((View.OnClickListener) this);
        this.activityAddRecipeBinding.accountCategory.setOnClickListener((View.OnClickListener) this);
        this.activityAddRecipeBinding.btnNextStep.setOnClickListener((View.OnClickListener) this);
    }

    private void setIngredientAddAdapter() {
        this.ingredientAddAdapter = new IngredientAddAdapter(ingredients, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.activityAddRecipeBinding.ingredientAddList.setLayoutManager(layoutManager);
        activityAddRecipeBinding.ingredientAddList.setHasFixedSize(false);

        this.activityAddRecipeBinding.ingredientAddList.setAdapter(ingredientAddAdapter);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.activityAddRecipeBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_recipe);
        this.ingredients.add(new Ingredient("", ""));
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        this.activityAddRecipeBinding.toolbarTitle.setText("Add Recipe");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                Intent intent = new Intent(AddRecipeActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_next_step:
                Intent intent2 = new Intent(AddRecipeActivity.this, CookingStepsActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                break;

            case R.id.account_category:

                DialogFragment dialog = CategorySearchDialog.newInstance();
                ((CategorySearchDialog) dialog).setCallback(new CategorySearchDialog.Callback() {
                    @Override
                    public void onActionClick(String name) {
                        Toast.makeText(AddRecipeActivity.this, name, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(this.getSupportFragmentManager(), "tag");
                break;

            case R.id.btn_add_ingredient:
                this.ingredients.add(new Ingredient("", ""));
                this.ingredientAddAdapter.notifyItemInserted(ingredients.size() - 1);
        }
    }

    @Override
    public void onIngredientRemove(int id) {
        this.ingredients.remove(id);
        this.ingredientAddAdapter.notifyItemRemoved(id);
    }
}