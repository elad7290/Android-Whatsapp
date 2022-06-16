package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.repositories.ChatsRepository;
import java.util.List;


public class ChatsViewModel extends ViewModel {

    private  ChatsRepository repository;
    private  LiveData<List<Chat>> chats;
    private String token;

    public ChatsViewModel() {
        //TODO: how to get token in constructor
    }


    public void setToken(String token){
        this.token=token;
        this.repository = new ChatsRepository(token);
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

    /*public void reload() {
        repository.reload();
    }*/

}
