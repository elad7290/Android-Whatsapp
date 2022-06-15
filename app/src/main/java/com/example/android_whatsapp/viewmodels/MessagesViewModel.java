package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_whatsapp.entities.Message;
import com.example.android_whatsapp.repositories.MessagesRepository;
import java.util.List;

public class MessagesViewModel extends ViewModel {

    private MessagesRepository repository;
    private LiveData<List<Message>> messages;
    private String username;

    public MessagesViewModel() {

    }

    public void setUsername(String username){
        this.username = username;
        this.repository = new MessagesRepository();
        repository.setUsername(username);
        messages = repository.getAll();
        reload();
    }

    public LiveData<List<Message>> get() {
        return messages;
    }

    public void add(Message message) {
        repository.add(message);

    }

    public void reload() {
        repository.reload();
    }

}
