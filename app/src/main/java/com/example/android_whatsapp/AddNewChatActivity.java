package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.android_whatsapp.data.AndroidWhatsappDB;
import com.example.android_whatsapp.data.ChatDao;
import com.example.android_whatsapp.databinding.ActivityAddNewChatBinding;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.viewmodels.ChatsViewModel;

public class AddNewChatActivity extends AppCompatActivity {

    private ActivityAddNewChatBinding binding;
    private EditText username;
    private EditText nickname;
    private EditText sever;
    private ChatsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel=new ViewModelProvider(this).get(ChatsViewModel.class);

        username=binding.username;
        nickname=binding.nickname;
        sever=binding.server;

        binding.btnAdd.setOnClickListener(view -> {
            String u= username.getText().toString();
            String n= nickname.getText().toString();
            String s= sever.getText().toString();

            Chat chat =new Chat(u,n,s,null,null);
            viewModel.add(chat);
            finish();
        });





    }
}