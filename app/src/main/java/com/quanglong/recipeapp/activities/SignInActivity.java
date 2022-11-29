package com.quanglong.recipeapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.LoginRequest;
import com.quanglong.recipeapp.model.User;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.utilities.StatusBarConfig;
import com.quanglong.recipeapp.utilities.UserLocalStore;
import com.quanglong.recipeapp.viewmodels.UserViewModel;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private TextView txt_sign_up;
    private Button btn_sign_in;
    private EditText edt_email;
    private EditText edt_password;
    private UserViewModel viewModel;
    private boolean isAllFieldsChecked = false;
    private UserLocalStore userLocalStore;
    private String tokenDevice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        StatusBarConfig.StatusBarCustom(this);

        doInitialization();

        this.txt_sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        this.btn_sign_in.setOnClickListener(view -> {
            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                SignIn();
            }
        });

       GetDeviceToken();
    }

    private void doInitialization() {
        this.txt_sign_up = findViewById(R.id.txt_sign_up);
        this.btn_sign_in = findViewById(R.id.btn_sign_in);
        this.edt_email = findViewById(R.id.edt_email);
        this.edt_password = findViewById(R.id.edt_password);
        this.viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        this.userLocalStore = new UserLocalStore(this);
    }

    private void SignIn(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginUser(edt_email.getText().toString());
        loginRequest.setPassword(edt_password.getText().toString());
        loginRequest.setDeviceName(Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
        loginRequest.setTokenDevice(tokenDevice);

        viewModel.login(loginRequest).observe(this, new Observer<UserLoginResponse>() {
            @Override
            public void onChanged(UserLoginResponse res) {
                if (res != null){
                    if (res.getStatusCode() == 0){
                        userLocalStore.storeUserData(res);
                        userLocalStore.setUserLoggedIn(true);

                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignInActivity.this, res.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void GetDeviceToken() {

        // Get Device Token
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        tokenDevice = task.getResult();
                    }
                });
    }

    private boolean CheckAllFields() {

        if (edt_email.length() == 0) {
            edt_email.setError("This field is required");
            return false;
        }

        if (edt_password.length() == 0) {
            edt_password.setError("This field is required");
            return false;
        }

        return true;
    }
}