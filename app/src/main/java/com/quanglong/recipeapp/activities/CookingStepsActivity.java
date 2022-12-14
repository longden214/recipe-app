package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
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
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.Step;
import com.quanglong.recipeapp.responses.RecipeAddResponse;
import com.quanglong.recipeapp.utilities.FCMSend;
import com.quanglong.recipeapp.utilities.RealPathUtil;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CookingStepsActivity extends AppCompatActivity implements View.OnClickListener, StepListener {
    private Toolbar toolbar;
    private ActivityCookingStepsBinding binding;
    private List<Step> steps = new ArrayList<Step>();
    private StepAddAdapter stepAddAdapter;
    private RecipeDataRequest recipe_request;
    private RecipeViewModel viewModel;
    private Uri img_uri;

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
        this.img_uri = Uri.parse(getIntent().getStringExtra("imageUri"));

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
                if (checkAllField()){
                    recipe_request.setListSteps(steps);

                    viewModel.createRecipe(recipe_request).observe(this, new Observer<RecipeAddResponse>() {
                        @Override
                        public void onChanged(RecipeAddResponse s) {
                            if (s.getMessage().equals("Success!")){
                                Toast.makeText(getApplicationContext(), "Create recipe successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(CookingStepsActivity.this, MainActivity.class);
                                startActivity(intent);

                                List<Notifications> notificationModels = s.getNotificationModels();

                                if (notificationModels.size() > 0){
                                    for (Notifications item : notificationModels) {
                                        if (item.getListTokenDevice().size() > 0){
                                            for (String itemToken: item.getListTokenDevice()) {
                                                FCMSend.pushNotification(
                                                        CookingStepsActivity.this,
                                                        itemToken,
                                                        item.getNotificationType(),
                                                        item.getDescription()
                                                );
                                            }
                                        }
                                    }
                                }
                            }else{
                                if (s.getMessage().equals("Failed!")){
                                    Toast.makeText(CookingStepsActivity.this, "Create recipe failed!", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(CookingStepsActivity.this, s.getMessage(), Toast.LENGTH_LONG).show();
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
        this.steps.remove(id);
        this.stepAddAdapter.notifyItemRemoved(id);
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