package com.quanglong.recipeapp.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.AccountSettingRequest;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.UserViewModel;

public class AccountActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    Toolbar toolbar;
    TextView tv_title;
    private TextView btn_save;
    private EditText itemEmail;
    private EditText itemPhone;
    private EditText itemJob;
    private EditText itemAddress;
    private RadioGroup radioGroup;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private SharedPreferences userLocalDatabase;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        StatusBarConfig.StatusBarCustom(this);
        doInitialization();
        customActionBar();

        setUserInfo();

        btn_save.setOnClickListener(view -> {
            if (CheckAllFields()) {
                AccountSettingRequest request = new AccountSettingRequest();
                request.setId(userLocalDatabase.getInt("id", -1));
                request.setEmail(itemEmail.getText().toString());
                request.setPhoneNumber(itemPhone.getText().toString());
                request.setJob(itemJob.getText().toString());
                request.setAddress(itemAddress.getText().toString());

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.radioMale) {
                    request.setSex(0);
                } else {
                    request.setSex(1);
                }

                userViewModel.accountSetting(request).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s.equals("Success!")) {
                            Toast.makeText(AccountActivity.this, "Update account successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AccountActivity.this, "Update account failed!", Toast.LENGTH_SHORT).show();
                        }

//                        startActivity(new Intent(AccountActivity.this, SettingActivity.class));
//                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                });
            }
        });
    }

    private void setUserInfo() {
        userViewModel.userDetail(userLocalDatabase.getInt("id", -1), userLocalDatabase.getInt("id", -1)).observe(this, new Observer<UserLoginResponse>() {
                    @Override
                    public void onChanged(UserLoginResponse userLoginResponse) {
                        if (userLoginResponse != null) {
                            itemEmail.setText(userLoginResponse.getEmail());
                            itemPhone.setText(userLoginResponse.getPhoneNumber());
                            itemJob.setText(userLoginResponse.getJob());
                            itemAddress.setText(userLoginResponse.getAddress());

                            if (userLoginResponse.getSex() == 0) {
                                radioMale.setChecked(true);
                                radioFemale.setChecked(false);
                            } else {
                                radioMale.setChecked(false);
                                radioFemale.setChecked(true);
                            }
                        }
                    }
                }
        );
    }

    private void customActionBar() {
        setSupportActionBar(toolbar);
        this.tv_title.setText("Account");
        getSupportActionBar().setTitle("");

        // Customize the back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left);
        // showing the back button in action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void doInitialization() {
        this.toolbar = findViewById(R.id.toolbar);
        this.tv_title = findViewById(R.id.toolbar_title);
        btn_save = findViewById(R.id.fullscreen_dialog_save);
        itemEmail = findViewById(R.id.itemEmail);
        itemPhone = findViewById(R.id.itemPhone);
        itemJob = findViewById(R.id.itemJob);
        itemAddress = findViewById(R.id.itemAddress);
        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        this.userLocalDatabase = getSharedPreferences("userDetails", 0);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean CheckAllFields() {
        if (itemEmail.length() == 0) {
            itemEmail.setError("This field is required");
            return false;
        }

        if (itemEmail.length() == 0) {
            itemEmail.setError("This field is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(itemEmail.getText().toString()).matches()) {
            itemEmail.setError("Invalid email address");
            return false;
        }

        if (itemPhone.length() == 0) {
            itemPhone.setError("This field is required");
            return false;
        } else if (!itemPhone.getText().toString().matches("^[0-9]{10,13}$")) {
            itemPhone.setError("The phone is invalid");
            return false;
        }

        return true;
    }
}