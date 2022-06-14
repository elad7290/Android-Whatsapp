package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.android_whatsapp.adapters.ChatsListAdapter;
import com.example.android_whatsapp.databinding.ActivitySidebarBinding;
import com.example.android_whatsapp.entities.Chat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SidebarActivity extends AppCompatActivity {
    private ActivitySidebarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySidebarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        RecyclerView chatsList = binding.chatsList;
        final ChatsListAdapter adapter = new ChatsListAdapter(this);
        chatsList.setAdapter(adapter);
        chatsList.setLayoutManager(new LinearLayoutManager(this));

        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat("1", "elad", "....", "messaskdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("2", "elad2", "....", "messaskdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("3", "elad3", "....", "messa2skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("4", "elad4", "....", "messa234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("5", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("6", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("7", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("8", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("9", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("10", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("11", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("12", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("13", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("14", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("15", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));
        chats.add(new Chat("16", "elad5", "....", "messax234skdh", Calendar.getInstance().getTime().toString()));

        adapter.setChats(chats);
    }
}