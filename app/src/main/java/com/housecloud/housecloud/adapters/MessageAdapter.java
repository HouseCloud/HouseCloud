package com.housecloud.housecloud.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.housecloud.housecloud.model.Messages;
import com.housecloud.housecloud.R;

import java.util.List;

/**
 * Created by alberto on 4/3/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private List<Messages> mMessageList;
    private FirebaseAuth mAuth;

    public MessageAdapter(List<Messages> mMessageList) {
        this.mMessageList = mMessageList;

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_single_layout, parent, false);



        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        String current_user_id = mAuth.getCurrentUser().getUid();

        Messages c = mMessageList.get(position);

        String from_user = c.getFrom();

        if(from_user.equals(current_user_id)){
            holder.messagesText.setBackgroundColor(Color.WHITE);
            holder.messagesText.setTextColor(Color.BLACK);
        }else{
            holder.messagesText.setBackgroundResource(R.drawable.message_text_background);
            holder.messagesText.setTextColor(Color.WHITE);
        }

        holder.messagesText.setText(c.getMessage());

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public TextView messagesText;
        //public TextView timeText;

        public MessageViewHolder(View v) {
            super(v);

            messagesText = v.findViewById(R.id.single_message);
        }
    }
}
