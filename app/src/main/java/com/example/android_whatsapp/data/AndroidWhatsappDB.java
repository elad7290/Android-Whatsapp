package com.example.android_whatsapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_whatsapp.entities.Chat;

@Database(entities = {Chat.class},version = 1)
public abstract class AndroidWhatsappDB extends RoomDatabase {

    public abstract ChatDao chatDao();

}
