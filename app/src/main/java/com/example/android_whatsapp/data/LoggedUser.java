package com.example.android_whatsapp.data;

import com.example.android_whatsapp.entities.User;

public class LoggedUser {

    private static LoggedUser singleton = null;
    private String username = null;

    private LoggedUser() {
    }

    public static LoggedUser getInstance() {
        if (singleton == null) {
            singleton = new LoggedUser();
        }
        return singleton;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
