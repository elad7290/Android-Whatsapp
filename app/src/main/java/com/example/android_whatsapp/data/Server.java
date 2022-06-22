package com.example.android_whatsapp.data;

public class Server {

    public static String address = "http://10.0.2.2:7097/api/";

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Server.address = "http://"+address+"/api/";
    }
}
