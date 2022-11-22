package com.quanglong.recipeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    boolean isAllFieldsChecked = false;

    private TextView txt_sign_in;
    private Button btn_sign_up;
    private EditText edt_name;
    private EditText edt_email;
    private EditText edt_phone;
    private EditText edt_password;
    private EditText edt_cf_password;

    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        StatusBarConfig.StatusBarCustom(this);

        doInitialization();

        this.txt_sign_in.setOnClickListener(view -> {

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

        this.btn_sign_up.setOnClickListener(view -> {
            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                RegisterUser();
            }
        });
    }

    private void doInitialization() {
        this.txt_sign_in = findViewById(R.id.txt_sign_in);
        this.btn_sign_up = findViewById(R.id.btn_sign_up);
        this.edt_name = findViewById(R.id.edt_name);
        this.edt_email = findViewById(R.id.edt_email);
        this.edt_phone = findViewById(R.id.edt_phone);
        this.edt_password = findViewById(R.id.edt_password);
        this.edt_cf_password = findViewById(R.id.edt_confirm_password);
        this.viewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    public void RegisterUser(){
        User user = new User();
        user.setUserName((this.edt_email.getText().toString().split("@"))[0]);
        user.setDisplayName(this.edt_name.getText().toString());
        user.setSex(0);
        user.setAddress("");
        user.setPhoneNumber(this.edt_phone.getText().toString());
        user.setPassword(this.edt_password.getText().toString());
        user.setEmail(this.edt_email.getText().toString());
        user.setJob("");
        user.setRole(0);
        user.setImageInput(new ArrayList<>());
        user.setDescription("");
        user.setCreateUser(0);

        viewModel.createUser(user).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("Success!")){
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }else{
                    if (s.equals("Failed!")){
                        Toast.makeText(SignUpActivity.this, "User registration failed!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SignUpActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private boolean CheckAllFields() {
        if (edt_name.length() == 0) {
            edt_name.setError("This field is required");
            return false;
        }

        if (edt_email.length() == 0) {
            edt_email.setError("This field is required");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString()).matches()){
            edt_email.setError("Invalid email address");
            return false;
        }

        if (edt_phone.length() == 0) {
            edt_phone.setError("This field is required");
            return false;
        }else if(!edt_phone.getText().toString().matches("^[0-9]{10,13}$")){
            edt_phone.setError("The phone is invalid");
            return false;
        }

        if (edt_password.length() == 0) {
            edt_password.setError("This field is required");
            return false;
        } else if (!Pattern.compile("^(?=.*[a-zA-Z0-9])(?=\\S+$).{8,}$")
                .matcher(edt_password.getText().toString()).matches()) {
            edt_password.setError("The password must least 8 characters");
            return false;
        }

        if (!edt_password.getText().toString().equals(edt_cf_password.getText().toString())) {
            edt_cf_password.setError("Passwords must macth.");
            return false;
        }

        return true;
    }
}