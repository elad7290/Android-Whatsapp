package com.example.android_whatsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_whatsapp.ChatActivity;
import com.example.android_whatsapp.R;
import com.example.android_whatsapp.RegisterActivity;
import com.example.android_whatsapp.entities.Chat;
import com.example.android_whatsapp.entities.Message;

import java.util.List;


public class ChatsListAdapter extends RecyclerView.Adapter<ChatsListAdapter.ChatViewHolder> {

    class ChatViewHolder extends RecyclerView.ViewHolder{

        private final TextView nickname;
        private final TextView lastMessage;
        private final TextView time;

        private ChatViewHolder(View itemView){
            super(itemView);
            nickname=itemView.findViewById(R.id.nickname);
            lastMessage=itemView.findViewById(R.id.lastMessage);
            time=itemView.findViewById(R.id.time);
        }
        public int clickAtPosition(){
            return getAdapterPosition();
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
        ChatViewHolder holder = new ChatViewHolder(itemView);

        // on item click listener
        itemView.setOnClickListener(view -> {
            if (chats != null) {
                int position = holder.clickAtPosition();
                final Chat current = chats.get(position);
                Context context = inflater.getContext();
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("nickname", current.getName());
                intent.putExtra("username", current.getId());
                context.startActivity(intent);
            }
        });

        return holder;
    }
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        if (chats != null) {
            final Chat current = chats.get(position);
            holder.nickname.setText(current.getName());
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
