package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_whatsapp.data.Server;
import com.example.android_whatsapp.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(view -> {
            String new_server = binding.server.getText().toString();
            if(!new_server.isEmpty()){
                Server.setAddress(new_server);
                binding.server.setText(null);
                finish();
            }
        });


    }
}