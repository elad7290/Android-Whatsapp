package com.example.android_whatsapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_whatsapp.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE id=:id")
    Message get(String id);

    @Insert
    void insert(Message... messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);
}

