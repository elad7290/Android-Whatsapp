package com.example.android_whatsapp.api;


import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("contacts")
    Call<List<Chat>> getChats();

    @POST("contacts")
    Call<Void> createChat(@Body Chat chat);

    @DELETE("contacts/{id}")
    Call<Void> deleteChat(@Path("id") int id);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Path("id") String id);

    @POST("contacts/{id}/messages")
    Call<Void> createMessage(@Path("id") String id, @Body Message message);

}
