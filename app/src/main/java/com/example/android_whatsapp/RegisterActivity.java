package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.android_whatsapp.databinding.ActivityRegisterBinding;
import com.example.android_whatsapp.viewmodels.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private EditText username = null;
    private EditText nickname = null;
    private EditText password = null;
    private EditText confirmPassword = null;
    private UserViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        username = binding.RegisterInputUsername;
        nickname = binding.RegisterInputNickname;
        password = binding.RegisterInputPassword;
        confirmPassword = binding.RegisterInputConfirmPassword;

        binding.linkLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnRegister.setOnClickListener(view -> {
            if(validateUsername() && validateNickname() && validatePassword() && validateConfirmPassword()){
                String u = username.getText().toString();
                String n = nickname.getText().toString();
                String p = password.getText().toString();
                viewModel.register(u,n,p);
            }
        });

        viewModel.getUser().observe(this, user-> {
            if(user!=null){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // username validation
        username.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateUsername();
            }
        });
        //nickname validation
        nickname.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateNickname();
            }
        });
        //password validation
        password.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validatePassword();
                if (!confirmPassword.getText().toString().isEmpty()) {
                    validateConfirmPassword();
                }
            }
        });
        //confirm password validation
        confirmPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateConfirmPassword();
            }
        });


    }

    private boolean validateUsername() {
        String val = username.getText().toString();
        String pattern = "^[A-Za-z]{3,16}";
        if (!val.matches(pattern)) {
            username.setError("Username should be 3-16 characters and should only include letters!");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validateNickname() {
        String val = nickname.getText().toString();
        String pattern = "^.{3,}";
        if (!val.matches(pattern)) {
            nickname.setError("Nickname should be at least 3 characters!");
            return false;
        } else {
            nickname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getText().toString();
        String pattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$";
        if (!val.matches(pattern)) {
            password.setError("Password should be 8-20 characters ant should include at least 1 letter, 1 number and 1 special character!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String val = confirmPassword.getText().toString();
        /*if (this.password == null) {
            binding.RegisterInputConfirmPassword.setError("Password field must be valid");
            return false;
        }*/
        if (!val.equals(password.getText().toString())) {
            confirmPassword.setError("Passwords don't match!");
            return false;
        } else {
            confirmPassword.setError(null);
            return true;
        }
    }

}