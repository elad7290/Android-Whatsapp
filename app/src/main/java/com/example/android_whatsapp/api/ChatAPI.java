package com.example.android_whatsapp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.data.AppContext;
import com.example.android_whatsapp.data.ChatDao;
import com.example.android_whatsapp.entities.Chat;

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
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ChatAPI(MutableLiveData<List<Chat>> mutableLiveData,ChatDao chatDao, String token)
    {
        this.chatListData=mutableLiveData;
        this.dao=chatDao;

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppContext.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Chat>> call = webServiceAPI.getChats();
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Chat>> call, @NonNull Response<List<Chat>> response) {
                new Thread(()->{
                    dao.clear();   // this may fund in the masgot of hani
                    dao.insert(response.body());
                    chatListData.postValue(dao.index());  // dao.index()?
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<List<Chat>> call, @NonNull Throwable t) {
                int u=0;

            }
        });
    }

    public void add(Chat chat) {
        Call<Void> call = webServiceAPI.createChat(chat);

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                new Thread(()->{
                    dao.insert(chat);
                    chatListData.postValue(dao.index());
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                int u=0;
            }
        });
    }
}
