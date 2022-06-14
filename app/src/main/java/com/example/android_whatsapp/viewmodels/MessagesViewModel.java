package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_whatsapp.entities.Message;
import com.example.android_whatsapp.repositories.MessagesRepository;
import java.util.List;

public class MessagesViewModel extends ViewModel {

    private final MessagesRepository repository;
    private final LiveData<List<Message>> messages;

    public MessagesViewModel() {
        this.repository = new MessagesRepository();
        messages = repository.getAll();
    }

    public LiveData<List<Message>> get() {
        return messages;
    }

    public void add(Message message) {
        repository.add(message);
    }

    public void delete(Message message) {
        repository.delete(message);
    }

    public void reload() {
        repository.reload();
    }

}
