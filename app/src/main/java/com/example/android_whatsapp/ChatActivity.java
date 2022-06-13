package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.android_whatsapp.adapters.MessagesListAdapter;
import com.example.android_whatsapp.databinding.ActivityChatBinding;
import com.example.android_whatsapp.entities.Message;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSend.setOnClickListener(view -> {
            String msg = binding.etSend.getText().toString();
            if (!msg.isEmpty()) {
                sendMessage();// fixe this....
            }
        });
        RecyclerView messagesList = binding.messagesList;
        final MessagesListAdapter adapter = new MessagesListAdapter(this);
        messagesList.setAdapter(adapter);
        messagesList.setLayoutManager(new LinearLayoutManager(this));



        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hi", Calendar.getInstance().getTime().toString(), false));
        messages.add(new Message("hi2", "...", true));
        messages.add(new Message("hi3", "...", false));
        messages.add(new Message("hi4", "...", true));
        messages.add(new Message("hi5jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj", "...", false));
        messages.add(new Message("hi", "...", false));
        messages.add(new Message("hiAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG2", "...", true));
        messages.add(new Message("hi3", "...", false));
        messages.add(new Message("hi4", "...", true));
        messages.add(new Message("hi5", "...", false));
        messages.add(new Message("hi", "...", false));
        messages.add(new Message("hi2", "...", true));
        messages.add(new Message("hi3", "...", false));
        messages.add(new Message("hi4", "...", true));
        messages.add(new Message("hi5", "...", false));
        adapter.setMessages(messages);
    }

    private void sendMessage() {
        //TODO: ADD MESSAGE TO DATABASE

    }
}