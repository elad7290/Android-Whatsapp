package com.example.android_whatsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.Message;

import java.util.List;


public class ChatsListAdapter extends RecyclerView.Adapter<ChatsListAdapter.ChatViewHolder> {

    class ChatViewHolder extends RecyclerView.ViewHolder{
        private final TextView username;
        private final TextView lastMessage;
        private final TextView time;

        private ChatViewHolder(View itemView){
            super(itemView);
            username=itemView.findViewById(R.id.username);
            lastMessage=itemView.findViewById(R.id.lastMessage);
            time=itemView.findViewById(R.id.time);
        }
    }

    private final LayoutInflater inflater;
    private List<Chat> chats;

    public ChatsListAdapter (Context context){
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView= inflater.inflate(R.layout.chat_layout,parent,false);
        return new ChatViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        if (chats != null) {
            final Chat current = chats.get(position);
            holder.username.setText(current.getName());
            holder.lastMessage.setText(current.getLast());
            holder.time.setText(current.getLastDate());
        }
    }

    public void setChats(List<Chat> c) {
        chats = c;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (chats != null) {
            return chats.size();
        }
        return 0;
    }

    public List<Chat> getChats() {
        return chats;
    }



}
