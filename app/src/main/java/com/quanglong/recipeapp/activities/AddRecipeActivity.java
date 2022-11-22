package com.quanglong.recipeapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.IngredientAddAdapter;
import com.quanglong.recipeapp.databinding.ActivityAddRecipeBinding;
import com.quanglong.recipeapp.fragments.CategorySearchDialog;
import com.quanglong.recipeapp.listener.IngredientListener;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.model.RecipeDataRequest;
import com.quanglong.recipeapp.model.Recipes;
import com.quanglong.recipeapp.utilities.Base64Config;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class AddRecipeActivity extends AppCompatActivity implements View.OnClickListener, IngredientListener, EasyPermissions.PermissionCallbacks {
    private Toolbar toolbar;
    private ActivityAddRecipeBinding activityAddRecipeBinding;
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private IngredientAddAdapter ingredientAddAdapter;
    private TextView category_selected;
    private ArrayList<Uri> images_picker = new ArrayList<>();
    private int category_select = -1;
    private Bitmap bitmap;

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
        this.activityAddRecipeBinding.btnPickImage.setOnClickListener((View.OnClickListener) this);
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
        this.category_selected = findViewById(R.id.category_selected);
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
                if (checkAllField()) {
                    SharedPreferences userLocalDatabase = this.getSharedPreferences("userDetails", 0);
                    try {
                        Uri filepath = images_picker.get(0);
                        InputStream inputStream = getContentResolver().openInputStream(filepath);
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    } catch (Exception ex) {
                    }

                    Recipes recipes = new Recipes();
                    recipes.setAuthorId(userLocalDatabase.getInt("id", -1));
                    recipes.setCategoryId(category_select);
                    recipes.setCookTime(activityAddRecipeBinding.cookTime.getText().toString());
                    recipes.setImageInput(Base64Config.Base64Split(encodeBitmapImage(bitmap)));
                    recipes.setName(activityAddRecipeBinding.edtName.getText().toString());
                    recipes.setOrigin(activityAddRecipeBinding.edtOrigin.getText().toString());
                    recipes.setServes(Integer.parseInt(activityAddRecipeBinding.edtServes.getText().toString()));
                    recipes.setCalories(Float.parseFloat(activityAddRecipeBinding.calories.getText().toString()));
                    recipes.setFat(Float.parseFloat(activityAddRecipeBinding.fat.getText().toString()));
                    recipes.setProtein(Float.parseFloat(activityAddRecipeBinding.protein.getText().toString()));
                    recipes.setCarbo(Float.parseFloat(activityAddRecipeBinding.carbohydrate.getText().toString()));
                    recipes.setCreateUser(userLocalDatabase.getInt("id", -1));

                    RecipeDataRequest data = new RecipeDataRequest();
                    data.setListInfgredients(ingredients);
                    data.setRecipe(recipes);

                    Intent intent2 = new Intent(AddRecipeActivity.this, CookingStepsActivity.class);
                    intent2.putExtra("recipe_request", data);
                    intent2.putExtra("imageUri", images_picker.get(0).toString());
                    startActivity(intent2);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                break;

            case R.id.account_category:
                DialogFragment dialog = CategorySearchDialog.newInstance();
                ((CategorySearchDialog) dialog).setCallback(new CategorySearchDialog.Callback() {
                    @Override
                    public void onActionClick(int id, String name) {
                        category_select = id;
                        category_selected.setTextColor(getResources().getColor(R.color.text_color));
                        category_selected.setText(name);
                    }
                });
                dialog.show(this.getSupportFragmentManager(), "tag");
                break;

            case R.id.btn_add_ingredient:
                this.ingredients.add(new Ingredient("", ""));
                this.ingredientAddAdapter.notifyItemInserted(ingredients.size() - 1);
                break;
            case R.id.btn_pick_image:
                images_picker.clear();
                // define camera & storange permissions
                String[] strings = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                // check condition
                if (EasyPermissions.hasPermissions(this, strings)) {
                    //when permissions are already granted
                    //create method
                    imagePicker();
                } else {
                    //when permission not granted
                    //Request permission
                    EasyPermissions.requestPermissions(
                            this,
                            "App needs access to your camera & storage",
                            100,
                            strings
                    );
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // handles the request result
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check condition
        if (resultCode == RESULT_OK && data != null) {
            //when activity contain data
            //check condition
            if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO) {
                //when request for photo
                //Initialize array list
                images_picker = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                // set image on container
                this.activityAddRecipeBinding.recipeImage.setImageURI(images_picker.get(0));
            }
        }

    }

    private void imagePicker() {
        // open picker
        FilePickerBuilder.getInstance()
                .setActivityTitle("Select Images")
                .setSpan(FilePickerConst.SPAN_TYPE.FOLDER_SPAN, 3)
                .setSpan(FilePickerConst.SPAN_TYPE.DETAIL_SPAN, 4)
                .setMaxCount(1)
                .setSelectedFiles(images_picker)
                .setActivityTheme(R.style.CustomPickerTheme)
                .pickPhoto(this);
    }

    @Override
    public void onIngredientRemove(int id) {
        this.ingredients.remove(id);
        this.ingredientAddAdapter.notifyItemRemoved(id);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        // check condition
        if (requestCode == 100 && perms.size() == 2) {
            //when permissions are granted
            //call method
            imagePicker();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // check condition
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //when permisstions denied multiple time
            //Open app setting
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            //when permissions deny once
            //Display toast
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkAllField() {
        if (images_picker.size() == 0) {
            Toast.makeText(getApplicationContext(), "Please select recipe image.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (activityAddRecipeBinding.edtName.getText().toString().length() == 0) {
            activityAddRecipeBinding.edtName.setError("This field is required");
            return false;
        }

        if (category_select == -1) {
            Toast.makeText(getApplicationContext(), "Please select category.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (activityAddRecipeBinding.cookTime.getText().toString().length() == 0) {
            activityAddRecipeBinding.cookTime.setError("This field is required");
            return false;
        }

        if (activityAddRecipeBinding.edtServes.getText().toString().length() == 0) {
            activityAddRecipeBinding.edtServes.setError("This field is required");
            return false;
        }

        if (activityAddRecipeBinding.edtOrigin.getText().toString().length() == 0) {
            activityAddRecipeBinding.edtOrigin.setError("This field is required");
            return false;
        }

        if (ingredients.size() > 0) {
            for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i).getName().equals("") || ingredients.get(i).getQuantity().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please complete all information ingredients.", Toast.LENGTH_SHORT);
                    toast.show();
                    return false;
                }
            }
        }

        if (activityAddRecipeBinding.protein.getText().toString().length() == 0) {
            activityAddRecipeBinding.protein.setError("This field is required");
            return false;
        }

        if (activityAddRecipeBinding.calories.getText().toString().length() == 0) {
            activityAddRecipeBinding.calories.setError("This field is required");
            return false;
        }

        if (activityAddRecipeBinding.fat.getText().toString().length() == 0) {
            activityAddRecipeBinding.fat.setError("This field is required");
            return false;
        }

        if (activityAddRecipeBinding.carbohydrate.getText().toString().length() == 0) {
            activityAddRecipeBinding.carbohydrate.setError("This field is required");
            return false;
        }

        return true;
    }

    private String encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }
}