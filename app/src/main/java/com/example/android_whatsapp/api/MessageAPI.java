package com.example.android_whatsapp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.data.AppContext;
import com.example.android_whatsapp.data.ChatDao;
import com.example.android_whatsapp.data.MessageDao;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.Message;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {

    private MutableLiveData<List<Message>> messageListData;
    private MessageDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessageAPI(MutableLiveData<List<Message>> mutableLiveData, MessageDao messageDao) {
        this.messageListData = mutableLiveData;
        this.dao = messageDao;

        retrofit = new Retrofit.Builder()
                .baseUrl(AppContext.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(String username) {
        Call<List<Message>> call = webServiceAPI.getMessages(username);

        call.enqueue(new Callback<List<Message>>() {

            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                new Thread(()->{
                    dao.clear();
                    dao.insert(response.body());
                    messageListData.postValue(dao.index());
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {
                // TODO: delete in all failure methods
                int u=0;
            }
        });
    }

    public void add(String username, Message message) {
        Call<Void> call = webServiceAPI.createMessage(username, message);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                new Thread(()->{
                    dao.insert(message);
                    messageListData.postValue(dao.index());
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                int u=0;
            }
        });
    }
}
