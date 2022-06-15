package com.example.android_whatsapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.Message;

@Database(entities = {Chat.class, Message.class},version = 12)
public abstract class AndroidWhatsappDB extends RoomDatabase {

    public abstract ChatDao chatDao();
    public abstract MessageDao messageDao();

}
