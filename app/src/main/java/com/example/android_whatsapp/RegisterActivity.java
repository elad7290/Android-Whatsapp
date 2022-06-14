package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_whatsapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private String username = null;
    private String nickname = null;
    private String password = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.linkLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        // username validation
        binding.RegisterInputUsername.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateUsername();
            }
        });
        //nickname validation
        binding.RegisterInputNickname.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateNickname();
            }
        });
        //password validation
        binding.RegisterInputPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validatePassword();
                if (!binding.RegisterInputConfirmPassword.getText().toString().isEmpty()) {
                    validateConfirmPassword();
                }
            }
        });
        //confirm password validation
        binding.RegisterInputConfirmPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                validateConfirmPassword();
            }
        });


    }

    private boolean validateUsername() {
        String val = binding.RegisterInputUsername.getText().toString();
        String pattern = "^[A-Za-z]{3,16}";
        if (!val.matches(pattern)) {
            binding.RegisterInputUsername.setError("Username should be 3-16 characters and should only include letters!");
            return false;
        } else {
            this.username = val;
            binding.RegisterInputUsername.setError(null);
            return true;
        }
    }

    private boolean validateNickname() {
        String val = binding.RegisterInputNickname.getText().toString();
        String pattern = "^.{3,}";
        if (!val.matches(pattern)) {
            binding.RegisterInputNickname.setError("Nickname should be at least 3 characters!");
            return false;
        } else {
            this.nickname = val;
            binding.RegisterInputNickname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = binding.RegisterInputPassword.getText().toString();
        String pattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$";
        if (!val.matches(pattern)) {
            binding.RegisterInputPassword.setError("Password should be 8-20 characters ant should include at least 1 letter, 1 number and 1 special character!");
            return false;
        } else {
            this.password = val;
            binding.RegisterInputPassword.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String val = binding.RegisterInputConfirmPassword.getText().toString();
        if (this.password == null) {
            binding.RegisterInputConfirmPassword.setError("Password field must be valid");
            return false;
        }
        if (!val.equals(this.password)) {
            binding.RegisterInputConfirmPassword.setError("Passwords don't match!");
            return false;
        } else {
            binding.RegisterInputConfirmPassword.setError(null);
            return true;
        }
    }

}