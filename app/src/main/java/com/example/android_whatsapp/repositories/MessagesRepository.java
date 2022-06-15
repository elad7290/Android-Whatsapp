package com.example.android_whatsapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.api.MessageAPI;
import com.example.android_whatsapp.data.LocalDB;
import com.example.android_whatsapp.data.MessageDao;
import com.example.android_whatsapp.entities.Message;
import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {

    private MessageDao dao;
    private MessageListData messageListData;
    private MessageAPI api;
    private String username;

    public MessagesRepository() {

    }

    public void setUsername(String username) {
        this.username = username;
        LocalDB db = LocalDB.getInstance();
        dao = db.messageDao();
        messageListData = new MessageListData();
        api = new MessageAPI(messageListData, dao);
        /*List<Message> messages1 = dao.index();
        List<Message> messages2 = dao.getMessagesFrom(username);*/
    }

    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData() {
            super();
            new Thread(() -> postValue(new LinkedList<>()));
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                /*messageListData.postValue(dao.getMessagesFrom(username));*/
                messageListData.postValue(dao.getMessagesFrom(username));
            }).start();
        }
    }

    public LiveData<List<Message>> getAll() {
        List<Message> m= dao.index();
        return messageListData;
    }

    public void add(final Message message) {
        api.add(username, message);
    }

    public void reload() {
        api.get(username);
    }

}
