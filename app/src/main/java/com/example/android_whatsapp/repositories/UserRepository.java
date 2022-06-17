package com.example.android_whatsapp.repositories;

import android.service.autofill.UserData;

import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.api.ChatAPI;
import com.example.android_whatsapp.api.UserAPI;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.User;

import java.util.LinkedList;
import java.util.List;

public class UserRepository {

    private LoginData loginData;
    private RegisterData registerData;
    private UserAPI api;

    public UserRepository(){
        loginData=new LoginData();
        registerData = new RegisterData();
        api=new UserAPI(loginData, registerData);
    }

    class LoginData extends MutableLiveData<String> {

        public LoginData(){
            super();
            setValue(null);
        }
    }

    class RegisterData extends MutableLiveData<User> {

        public RegisterData(){
            super();
            setValue(null);
        }
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public RegisterData getRegisterData() {
        return registerData;
    }

    public void login(String username, String password){
        api.login(username,password);
    }

    public void register(String username, String nickname, String password){
        api.register(username,nickname,password);
    }

    //TODO: delete for logout maybe???
}
