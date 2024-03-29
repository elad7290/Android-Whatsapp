package com.example.android_whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;


import com.example.android_whatsapp.adapters.ChatsListAdapter;
import com.example.android_whatsapp.databinding.ActivitySidebarBinding;
import com.example.android_whatsapp.viewmodels.ChatsViewModel;

public class SidebarActivity extends AppCompatActivity {

    private ActivitySidebarBinding binding;
    private ChatsViewModel viewModel;
    private ChatsListAdapter adapter;
    private RecyclerView chatsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySidebarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel=new ViewModelProvider(this).get(ChatsViewModel.class);

        // create chats list
        chatsList = binding.chatsList;
        adapter = new ChatsListAdapter(this);
        chatsList.setAdapter(adapter);
        chatsList.setLayoutManager(new LinearLayoutManager(this));

        binding.btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddNewChatActivity.class);
            startActivity(intent);
        });

        binding.settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        viewModel.get().observe(this,chats -> adapter.setChats(chats));

    }

   /* @Override
    protected void onResume() {
        super.onResume();
        chats.clear();
        chats.addAll(chatDao.index());
        adapter.notifyDataSetChanged();
    }*/
}