package com.example.android_whatsapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_whatsapp.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Message> messages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Message message);

    @Query("DELETE FROM message")
    void clear();

   /* @Query("SELECT * FROM message WHERE id=:id")
    Message get(String id);

    @Update
    void update(Message messages);

    @Delete
    void delete(Message messages);*/
}

