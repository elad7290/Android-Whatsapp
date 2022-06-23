package com.example.android_whatsapp.data;

public class Server {

    //public static String address = "http://10.0.2.2:7097/api/";
    public static String address = "10.0.2.2:7097";

    public static String getAddress() {
        //return address;
        return "http://"+address+"/api/";
    }

    public static void setAddress(String address) {
        Server.address = address;
    }

    public static String getServer(){
        return address;
    }


}
