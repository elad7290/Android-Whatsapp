package com.example.android_whatsapp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.data.AppContext;
import com.example.android_whatsapp.data.LocalDB;
import com.example.android_whatsapp.data.LoggedUser;
import com.example.android_whatsapp.data.MessageDao;
import com.example.android_whatsapp.data.Server;
import com.example.android_whatsapp.data.Token;
import com.example.android_whatsapp.entities.Message;
import com.example.android_whatsapp.entities.Transfer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {

    private MutableLiveData<List<Message>> messageListData;
    private MessageDao dao;
    private OkHttpClient client;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessageAPI(MutableLiveData<List<Message>> mutableLiveData, MessageDao messageDao) {
        this.messageListData = mutableLiveData;
        this.dao = messageDao;

        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + Token.getInstance().getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Server.getAddress())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    private void validRetrofit(){
        retrofit = retrofit.newBuilder()
                .baseUrl(Server.getAddress())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

    }

    public void get(String username) {
        validRetrofit();

        Call<List<Message>> call = webServiceAPI.getMessages(username);

        call.enqueue(new Callback<List<Message>>() {

            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                if (response.code() == 200) {
                    new Thread(()->{
                        dao.clear(username);
                        List <Message> messages = response.body();
                        for (Message m: messages) {
                            m.setUsername(username);
                        }
                        dao.insert(messages);
                        List<Message> m = dao.index();
                        // id is not the same id as server
                        /*dao.insert(response.body());*/
                        messageListData.postValue(dao.getMessagesFrom(username));
                    }).start();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Message>> call, @NonNull Throwable t) {
                // TODO: delete in all failure methods
                int u=0;
            }
        });
    }

    public void add(String username, Message message) {
        // transfer and if succeed, add
        transfer(username, message);
    }

    private void transfer(String username, @NonNull Message message){
        Retrofit other_retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ LocalDB.getInstance().chatDao().get(username).getServer()+"/api/")
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServiceAPI other_webServiceAPI = other_retrofit.create(WebServiceAPI.class);


        // transfer
        Call<Void> call = other_webServiceAPI.transfer(new Transfer
                (LoggedUser.getInstance().getUsername(), username, message.getContent()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 201){
                    addChat(username, message);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                int i = 0;
            }
        });
    }

    private void addChat(String username, Message message){
        validRetrofit();

        Call<Void> call = webServiceAPI.createMessage(username, message);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code()==201){
                    new Thread(()->{
                        dao.insert(message);
                        messageListData.postValue(dao.getMessagesFrom(username));
                    }).start();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                int u=0;
            }
        });
    }
}
