package com.quanglong.recipeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.StepAddAdapter;
import com.quanglong.recipeapp.databinding.ActivityCookingStepsBinding;
import com.quanglong.recipeapp.listener.StepListener;
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.Step;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;

public class EditCookingStepsActivity extends AppCompatActivity implements View.OnClickListener, StepListener {
    private Toolbar toolbar;
    private ActivityCookingStepsBinding binding;
    private List<Step> steps = new ArrayList<Step>();
    private StepAddAdapter stepAddAdapter;
    private RecipeDataRequest recipe_request;
    private RecipeViewModel viewModel;

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
        this.viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        this.recipe_request = (RecipeDataRequest) getIntent().getSerializableExtra("recipe_request");

        if (recipe_request.getListSteps().size() > 0) {
            this.steps = recipe_request.getListSteps();
        } else {
            Step st = new Step();
            st.setDescription("");
            this.steps.add(st);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                break;
            case R.id.btn_finish:
                if (checkAllField()) {
                    recipe_request.setListSteps(steps);

                    viewModel.updateRecipe(recipe_request).observe(this, new Observer<RecipeAddResponse>() {
                        @Override
                        public void onChanged(RecipeAddResponse s) {
                            if (s != null) {
                                if (s.getMessage().equals("Success!")) {
                                    Toast.makeText(getApplicationContext(), "Update recipe successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(EditCookingStepsActivity.this, MainActivity.class);
                                    startActivity(intent);

                                    List<Notifications> notificationModels = s.getNotificationModels();
                                } else {
                                    if (s.getMessage().equals("Failed!")) {
                                        Toast.makeText(EditCookingStepsActivity.this, "Update recipe failed!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(EditCookingStepsActivity.this, s.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    });
                }

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
        this.steps.remove(id - 1);
        this.stepAddAdapter.notifyItemRemoved(id - 1);
        this.stepAddAdapter.notifyItemRangeChanged(id - 1, steps.size());
    }

    private boolean checkAllField() {
        if (steps.size() > 0) {
            for (int i = 0; i < steps.size(); i++) {
                if (steps.get(i).getDescription().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please complete all information steps.", Toast.LENGTH_SHORT);
                    toast.show();
                    return false;
                }
            }
        }

        return true;
    }
}