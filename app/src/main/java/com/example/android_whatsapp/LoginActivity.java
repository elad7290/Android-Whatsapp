package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_whatsapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String username = null;
    private String password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.linkRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
        // username validation
        binding.loginInputUsername.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateUsername();
            }
        });
        // password validation
        binding.loginInputPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validatePassword();
            }
        });

        binding.btnLogin.setOnClickListener(view -> {
            // TODO: check user exist

            Intent intent = new Intent(this, SidebarActivity.class);
            startActivity(intent);
        });
    }

    private boolean validateUsername() {
        String val = binding.loginInputUsername.getText().toString();
        if (val.isEmpty()) {
            binding.loginInputUsername.setError("This field cannot be empty");
            return false;
        } else {
            this.username = val;
            binding.loginInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = binding.loginInputPassword.getText().toString();
        if (val.isEmpty()) {
            binding.loginInputPassword.setError("This field cannot be empty");
            return false;
        } else {
            this.password = val;
            binding.loginInputPassword.setError(null);
            return true;
        }
    }
}