package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.repositories.ChatsRepository;
import java.util.List;


public class ChatsViewModel extends ViewModel {

    private final ChatsRepository repository;
    private final LiveData<List<Chat>> chats;

    public ChatsViewModel() {
        this.repository = new ChatsRepository();
        chats=repository.getAll();
    }

    public LiveData<List<Chat>> get() {
        return chats;
    }

    public void add(Chat chat) {
        repository.add(chat);
    }

    public void delete(Chat chat) {
        repository.delete(chat);
    }

    public void reload() {
        repository.reload();
    }

}
