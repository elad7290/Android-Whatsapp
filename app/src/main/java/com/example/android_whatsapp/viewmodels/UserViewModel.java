package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_whatsapp.entities.User;
import com.example.android_whatsapp.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final LiveData<String> token;
    private final LiveData<User> user;

    public UserViewModel(){
        userRepository=new UserRepository();
        token=userRepository.getLoginData();
        user=userRepository.getRegisterData();
    }

    public LiveData<String> getToken(){
        return this.token;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void login(String username, String password){
         userRepository.login(username,password);
    }

    public void register(String username, String nickname, String password){
         userRepository.register(username,nickname,password);
    }

    //TODO: add delete?
}
