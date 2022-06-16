package com.example.android_whatsapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_whatsapp.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final LiveData<String> token;

    public UserViewModel(){
        userRepository=new UserRepository();
        token=userRepository.getUserData();
    }

    public LiveData<String> getToken(){
        return this.token;
    }

    public void login(String username,String password){
         userRepository.login(username,password);
    }

    //TODO: add delete?
}
