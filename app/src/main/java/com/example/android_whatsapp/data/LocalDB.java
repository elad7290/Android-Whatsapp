package com.example.android_whatsapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

public class LocalDB {

    private static LocalDB localDB = null;
    private AndroidWhatsappDB db = null;

    private LocalDB() {
        db = Room
                .databaseBuilder(AppContext.context, AndroidWhatsappDB.class, "ChatsDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    public static LocalDB getInstance() {
        // create object if it's not already created
        if (localDB == null) {
            localDB = new LocalDB();
        }
        // returns the singleton object
        return localDB;
    }

    public ChatDao chatDao() {
        return db.chatDao();
    }

    public MessageDao messageDao() {
        return db.messageDao();
    }
}