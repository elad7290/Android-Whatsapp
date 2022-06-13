package com.example.android_whatsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_whatsapp.R;
import com.example.android_whatsapp.entities.Message;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {

    public static final int MSG_TYPE_RECEIEVER = 0;
    public static final int MSG_TYPE_SENDER = 1;

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView content;
        private final TextView time;

        private MessageViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
        }

    }

    private final LayoutInflater inflater;
    private List<Message> messages;

    public MessagesListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == MSG_TYPE_RECEIEVER) {
            itemView = inflater.inflate(R.layout.message_layout_receiver, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.message_layout_sender, parent, false);
        }

        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            holder.content.setText(current.getContent());
            holder.time.setText(current.getCreated());
        }
    }

    public void setMessages(List<Message> m) {
        messages = m;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        }
        return 0;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public int getItemViewType(int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            if (current != null) {
                if (current.isSent()) {
                    return MSG_TYPE_SENDER;
                } else {
                    return MSG_TYPE_RECEIEVER;
                }
            }
        }
        // need to fix later
        return 0;
    }

}
