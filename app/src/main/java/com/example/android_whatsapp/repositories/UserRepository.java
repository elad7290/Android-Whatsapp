package com.example.android_whatsapp.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.api.ChatAPI;
import com.example.android_whatsapp.api.UserAPI;
import com.example.android_whatsapp.entities.Chat;

import java.util.LinkedList;
import java.util.List;

public class UserRepository {

    private UserData userData;
    private UserAPI api;

    public UserRepository(){
        userData=new UserData();
        api=new UserAPI(userData);
    }

    class UserData extends MutableLiveData<String> {

        public UserData(){
            super();
            setValue(null);
        }
    }

    public UserData getUserData() {
        return userData;
    }

    public void login(String username,String password){
        api.login(username,password);
    }

    //TODO: delete for logout maybe???
}
