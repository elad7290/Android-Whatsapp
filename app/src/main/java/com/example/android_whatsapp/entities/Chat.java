package com.example.android_whatsapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chat {
    @PrimaryKey
    @NonNull
    private String id;    // username
    private String name;  // nickname
    private String server;
    private String last;
    private String lastDate;

    /*public Chat(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = null;
        this.lastDate = null;
    }*/

    public Chat(@NonNull String id, String name, String server, String last, String lastDate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}
