package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.PasswordRequest;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.utilities.UserLocalStore;
import com.quanglong.recipeapp.viewmodels.UserViewModel;

import java.util.regex.Pattern;

public class PasswordActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tv_title;
    EditText item_pass_current;
    EditText item_pass_new;
    EditText item_pass_confirm;
    TextView btn_save;
    private SharedPreferences userLocalDatabase;
    private UserViewModel userViewModel;
    private UserLocalStore userLocalStore;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();
        customActionBar();

        this.btn_save.setOnClickListener(view -> {
            if (CheckAllFields()){
                PasswordRequest request = new PasswordRequest();
                request.setId(userLocalDatabase.getInt("id", -1));
                request.setOldPassword(item_pass_current.getText().toString());
                request.setNewPassword(item_pass_new.getText().toString());
                request.setUpdateId(userLocalDatabase.getInt("id", -1));

                userViewModel.updaePassword(request).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s.equals("Success!")){
                            Toast.makeText(PasswordActivity.this, "Change password successfully!", Toast.LENGTH_SHORT).show();

                            userLocalStore.clearUserData();
                            userLocalStore.setUserLoggedIn(false);
                            Intent loginIntent = new Intent(PasswordActivity.this, SignInActivity.class);
                            startActivity(loginIntent);
                        }else{
                            if (s.equals("Failed!")){
                                Toast.makeText(PasswordActivity.this, "Change password failed!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(PasswordActivity.this, s, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("Change password");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        this.item_pass_current = findViewById(R.id.item_pass_current);
        this.item_pass_new = findViewById(R.id.item_pass_new);
        this.item_pass_confirm = findViewById(R.id.item_pass_confirm);
        this.btn_save = findViewById(R.id.fullscreen_dialog_save);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        this.userLocalDatabase = getSharedPreferences("userDetails", 0);
        this.userLocalStore = new UserLocalStore(this);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean CheckAllFields() {

        if (item_pass_current.length() == 0) {
            item_pass_current.setError("This field is required");
            return false;
        }

        if (item_pass_new.length() == 0) {
            item_pass_new.setError("This field is required");
            return false;
        } else if (!Pattern.compile("^(?=.*[a-zA-Z0-9])(?=\\S+$).{8,}$")
                .matcher(item_pass_new.getText().toString()).matches()) {
            item_pass_new.setError("The password must least 8 characters");
            return false;
        }

        if (!item_pass_new.getText().toString().equals(item_pass_confirm.getText().toString())) {
            item_pass_confirm.setError("Passwords must macth.");
            return false;
        }

        return true;
    }
}