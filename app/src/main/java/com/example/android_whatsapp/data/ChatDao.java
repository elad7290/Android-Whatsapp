package com.example.android_whatsapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_whatsapp.entities.Chat;

import java.util.List;
@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat")
    List<Chat> index();

    @Query("SELECT * FROM chat WHERE id=:id")
    Chat get(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Chat> chats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Chat chat);

    @Update
    void update(Chat chat);

    @Delete
    void delete(Chat chat);

    @Query("DELETE FROM chat")
    void clear();

}
