package com.example.android_whatsapp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.data.AppContext;
import com.example.android_whatsapp.data.ChatDao;
import com.example.android_whatsapp.data.LoggedUser;
import com.example.android_whatsapp.data.Server;
import com.example.android_whatsapp.data.Token;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.Invitation;

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

public class ChatAPI {

    private MutableLiveData<List<Chat>> chatListData;
    private ChatDao dao;
    private OkHttpClient client;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ChatAPI(MutableLiveData<List<Chat>> mutableLiveData,ChatDao chatDao)
    {
        this.chatListData=mutableLiveData;
        this.dao=chatDao;

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

    public void get() {
        validRetrofit();

        Call<List<Chat>> call = webServiceAPI.getChats();
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Chat>> call, @NonNull Response<List<Chat>> response) {
                if (response.code() == 200) {
                    new Thread(()->{
                        dao.clear();   // this may fund in the masgot of hani
                        dao.insert(response.body());
                        chatListData.postValue(dao.index());  // dao.index()?
                    }).start();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Chat>> call, @NonNull Throwable t) {
                int u=0;

            }
        });
    }

    public void add(Chat chat) {
        // invite and if succeed, add
        invite(chat);
    }

    private void invite(@NonNull Chat chat){
        // make retrofit to another server
        Retrofit other_retrofit = new Retrofit.Builder()
                .baseUrl("http://"+chat.getServer()+"/api/")
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServiceAPI other_webServiceAPI = other_retrofit.create(WebServiceAPI.class);


        Call<Void> other_call = other_webServiceAPI.invite(new Invitation
                (LoggedUser.getInstance().getUsername(), chat.getId(), Server.getServer()));
        other_call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code()==201){
                    addChat(chat);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                int i = 0;
            }
        });
    }

    private void addChat(Chat chat){
        validRetrofit();

        // add to our server
        Call<Void> call = webServiceAPI.createChat(chat);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 201){
                    new Thread(()->{
                        dao.insert(chat);
                        chatListData.postValue(dao.index());
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
