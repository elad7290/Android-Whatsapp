package com.example.android_whatsapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.android_whatsapp.data.LocalDB;
import com.example.android_whatsapp.data.MessageDao;
import com.example.android_whatsapp.entities.Message;
import java.util.LinkedList;
import java.util.List;

public class MessagesRepository {

    private MessageDao dao;
    private MessageListData messageListData;
    //private MessageAPI api;

    public MessagesRepository() {
        LocalDB db = LocalDB.getInstance();
        dao = db.messageDao();
        messageListData = new MessageListData();
        // api = new MessageAPI(messageListData, dao);
    }

    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData() {
            super();
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                messageListData.postValue(dao.index());
            }).start();
        }
    }

    public LiveData<List<Message>> getAll() {
        return messageListData;
    }

    public void add(final Message message) {
        //api.add(message);
        int i=0;
    }

    public void delete(final Message message) {
        //api.delete(message);
    }

    public void reload() {
        //api.get();
    }

}
