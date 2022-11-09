package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.IngredientAddAdapter;
import com.quanglong.recipeapp.adapter.StepAddAdapter;
import com.quanglong.recipeapp.databinding.ActivityCookingStepsBinding;
import com.quanglong.recipeapp.fragments.CategorySearchDialog;
import com.quanglong.recipeapp.listener.StepListener;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.model.Step;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

import java.util.ArrayList;
import java.util.List;

public class CookingStepsActivity extends AppCompatActivity implements View.OnClickListener, StepListener {
    private Toolbar toolbar;
    private ActivityCookingStepsBinding binding;
    private List<Step> steps = new ArrayList<Step>();
    private StepAddAdapter stepAddAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_steps);
        StatusBarConfig.StatusBarCustom(this);

        doInitialization();
        customActionBar();
        setStepAdapter();
        setListener();

    }

    private void setListener() {
        this.binding.btnBack.setOnClickListener((View.OnClickListener) this);
        this.binding.btnAddStep.setOnClickListener((View.OnClickListener) this);
        this.binding.btnFinish.setOnClickListener((View.OnClickListener) this);
    }

    private void setStepAdapter() {
        this.stepAddAdapter = new StepAddAdapter(steps, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.binding.stepRecyclerView.setLayoutManager(layoutManager);
        binding.stepRecyclerView.setHasFixedSize(false);

        this.binding.stepRecyclerView.setAdapter(stepAddAdapter);
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        this.binding.toolbarTitle.setText("Cooking Steps");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_cooking_steps);

        Step st = new Step();
        st.setDescription("");
        this.steps.add(st);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                break;
            case R.id.btn_finish:
                Intent intent = new Intent(CookingStepsActivity.this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.btn_add_step:
                Step st = new Step();
                st.setDescription("");
                this.steps.add(st);
                this.stepAddAdapter.notifyItemInserted(steps.size() - 1);
                break;
        }
    }

    @Override
    public void onStepRemove(int id) {
        this.steps.remove(id);
        this.stepAddAdapter.notifyItemRemoved(id);
    }
}