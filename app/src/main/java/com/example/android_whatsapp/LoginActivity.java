package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.android_whatsapp.databinding.ActivityLoginBinding;
import com.example.android_whatsapp.viewmodels.MessagesViewModel;
import com.example.android_whatsapp.viewmodels.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private EditText username = null;
    private EditText password = null;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.linkRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        username=binding.loginInputUsername;
        password=binding.loginInputPassword;

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
            if(validateUsername()&&validatePassword()){
                String u=username.getText().toString();
                String p=password.getText().toString();
                viewModel.login(u,p);
            }
        });

        viewModel.getToken().observe(this, token-> {
            if(token!=null){
                Intent intent = new Intent(this, SidebarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("This field cannot be empty");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("This field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}