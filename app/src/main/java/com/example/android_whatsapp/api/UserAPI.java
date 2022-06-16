package com.example.android_whatsapp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.data.AppContext;
import com.example.android_whatsapp.data.Token;
import com.example.android_whatsapp.entities.LoginResponse;
import com.example.android_whatsapp.entities.Message;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    WebServiceAPI webServiceAPI;
    Retrofit retrofit;
    private MutableLiveData<String> token;


    public UserAPI(MutableLiveData<String> token) {
        this.token = token;
        retrofit = new Retrofit.Builder()
                .baseUrl(AppContext.context.getString(R.string.BaseUrl))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void login(String username, String password) {

        Call<String> call = webServiceAPI.Login(username, password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    String answer = response.body().toString();
                    Token.getInstance().setToken(answer);
                    token.postValue(answer);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                int i = 0;
            }
        });
    }


}
