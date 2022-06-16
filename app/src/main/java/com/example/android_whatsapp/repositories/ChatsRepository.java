package com.example.android_whatsapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.api.ChatAPI;
import com.example.android_whatsapp.data.ChatDao;
import com.example.android_whatsapp.data.LocalDB;
import com.example.android_whatsapp.entities.Chat;
import java.util.LinkedList;
import java.util.List;

public class ChatsRepository {

    private ChatDao dao;
    private ChatListData chatListData;
    private ChatAPI api;

    public ChatsRepository(String token){
        LocalDB db = LocalDB.getInstance();
        dao = db.chatDao();
        chatListData=new ChatListData();
        api = new ChatAPI(chatListData, dao,token);
    }

    class ChatListData extends MutableLiveData<List<Chat>>{

        public ChatListData(){
            super();
            new Thread(()-> postValue(new LinkedList<>())).start();

        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(()->{
                chatListData.postValue(dao.index());
                reload();
            }).start();
        }
    }

    public LiveData<List<Chat>> getAll(){
        return chatListData;
    }

    public void add(final Chat chat){
        api.add(chat);
    }

    public void delete(final Chat chat){
        //api.delete(chat);
    }

    public void reload(){
        api.get();
    }

}
