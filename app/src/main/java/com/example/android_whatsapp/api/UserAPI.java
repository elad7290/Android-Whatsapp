package com.example.android_whatsapp.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.data.AppContext;
import com.example.android_whatsapp.data.LoggedUser;
import com.example.android_whatsapp.data.Token;
import com.example.android_whatsapp.entities.Invitation;
import com.example.android_whatsapp.entities.User;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    WebServiceAPI webServiceAPI;
    Retrofit retrofit;
    private MutableLiveData<String> token;
    private MutableLiveData<User> user;


    public UserAPI(MutableLiveData<String> token, MutableLiveData<User> user) {
        this.token = token;
        this.user = user;
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
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.code() == 200) {
                    String answer = response.body().toString();
                    Token.getInstance().setToken(answer);

                    Call<String> call2 = webServiceAPI.getUserId("Bearer " + Token.getInstance().getToken());
                    call2.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            if(response.code() == 200){
                                String username = response.body();
                                LoggedUser.getInstance().setUsername(username);
                                // log in only if succeed
                                token.postValue(answer);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                            int i = 0;
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                int i = 0;
            }
        });




    }

    public void register(String username, String nickname, String password) {

        Call<Void> call = webServiceAPI.Register(new User(username, nickname, password));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 201) {
                    user.postValue(new User(username, nickname, password));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                int i = 0;
            }
        });
    }


}
