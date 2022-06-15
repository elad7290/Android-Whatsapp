package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.example.android_whatsapp.adapters.MessagesListAdapter;
import com.example.android_whatsapp.databinding.ActivityChatBinding;
import com.example.android_whatsapp.entities.Message;
import com.example.android_whatsapp.viewmodels.MessagesViewModel;
import java.util.Calendar;



public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private MessagesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        String username = intent.getStringExtra("username");
        if (nickname != null) {
            binding.nickname.setText(nickname);
        }

        viewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        viewModel.setUsername(username);


        binding.btnSend.setOnClickListener(view -> {
            sendMessage(username);
        });

        RecyclerView messagesList = binding.messagesList;
        final MessagesListAdapter adapter = new MessagesListAdapter(this);
        messagesList.setAdapter(adapter);
        messagesList.setLayoutManager(new LinearLayoutManager(this));



        viewModel.get().observe(this, messages -> {
            adapter.setMessages(messages);
        });

    }

    private void sendMessage(String username) {
        String content = binding.content.getText().toString();
        if (content.isEmpty()){
            return;
        }
        String created = Calendar.getInstance().getTime().toString();
        boolean sent = true;
        Message message = new Message(content, created, sent, username);
        viewModel.add(message);
        binding.content.setText(null);
    }
}