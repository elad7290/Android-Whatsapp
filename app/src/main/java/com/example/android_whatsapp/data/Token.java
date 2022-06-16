package com.example.android_whatsapp.data;

public class Token {

    private static Token singleton = null;
    private String token = null;

    private Token() {
    }

    public static Token getInstance() {
        // create object if it's not already created
        if (singleton == null) {
            singleton = new Token();
        }
        // returns the singleton object
        return singleton;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}