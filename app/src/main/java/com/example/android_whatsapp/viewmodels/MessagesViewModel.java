package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_whatsapp.entities.Message;
import com.example.android_whatsapp.repositories.MessagesRepository;
import java.util.List;

public class MessagesViewModel extends ViewModel {

    private final MessagesRepository repository;
    private final LiveData<List<Message>> messages;
    private String username;

    public MessagesViewModel() {
        this.repository = new MessagesRepository();
        messages = repository.getAll();
    }

    public void setUsername(String username){
        this.username = username;
        reload();
    }

    public LiveData<List<Message>> get() {
        return messages;
    }

    public void add(Message message) {
        repository.add(username, message);

    }

    public void reload() {
        repository.reload(username);
    }

}
