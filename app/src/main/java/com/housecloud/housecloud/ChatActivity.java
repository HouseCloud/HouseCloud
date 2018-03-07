package com.housecloud.housecloud;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private String mChatUser;
    private Toolbar mChatToolbar;
    private TextView chatUserName;
    private TextView chatUserStatus;

    private DatabaseReference mUserRef;
    private FirebaseAuth mAuth;
    String mCurrentUserId;

    EditText textChat;
    ImageButton sendChat;

    RecyclerView messeges_list;

    private final List<Messages> messageList = new ArrayList<Messages>();
    private LinearLayoutManager mLinearLayout;
    private MessageAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mChatToolbar = findViewById(R.id.chat_app_bar);
        setSupportActionBar(mChatToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        textChat = findViewById(R.id.textChat);
        sendChat = findViewById(R.id.sendChat);
        messeges_list = findViewById(R.id.messeges_list);
        mLinearLayout = new LinearLayoutManager(this);

        mAdapter = new MessageAdapter(messageList);

        messeges_list.setHasFixedSize(true);
        messeges_list.setLayoutManager(mLinearLayout);

        messeges_list.setAdapter(mAdapter);
        




        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View action_bar_view = inflater.inflate(R.layout.chat_custom_bar, null);
        actionBar.setCustomView(action_bar_view);

        chatUserName = findViewById(R.id.chat_name_user);
        chatUserStatus  = findViewById(R.id.chat_status_user);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserId = mAuth.getCurrentUser().getUid();





        mUserRef = FirebaseDatabase.getInstance().getReference();
        mChatUser = getIntent().getStringExtra("user_id");
        loadMessages();
        mUserRef.child("users").child(mChatUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                getSupportActionBar().setTitle("");
                chatUserName.setText(dataSnapshot.child("name").getValue().toString());
                chatUserStatus.setText(dataSnapshot.child("online").getValue().toString());

                if(!dataSnapshot.hasChild(mChatUser)){
                    Map chatAddMap = new HashMap();

                    chatAddMap.put("online", ServerValue.TIMESTAMP);

                    Map chatUserMap = new HashMap();
                    chatUserMap.put("Chat/"+mCurrentUserId+"/"+mChatUser, chatAddMap);
                    chatUserMap.put("Chat/"+mChatUser+"/"+mCurrentUserId, chatAddMap);

                    mUserRef.updateChildren(chatUserMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError!= null){
                                Log.d("Chat_Log", databaseError.getMessage().toString());
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    private void loadMessages() {

        mUserRef.child("messages").child(mCurrentUserId).child(mChatUser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messages message = dataSnapshot.getValue(Messages.class);
                messageList.add(message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void sendMessage (View v){
        String message = textChat.getText().toString();

        if(!TextUtils.isEmpty(message)){

            String current_user_ref = "messages/"+mCurrentUserId+"/"+mChatUser;
            String chat_user_ref = "messages/"+mChatUser+"/"+mCurrentUserId;

            DatabaseReference user_message_push = mUserRef.child("messages").child(mCurrentUserId).child(mChatUser).push();
            String push_id = user_message_push.getKey();

            Map messageMap = new HashMap();
            messageMap.put("message", message);
            messageMap.put("type", "text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("from", mCurrentUserId);

            Map messageUserMap = new HashMap();
            messageUserMap.put(current_user_ref+"/"+push_id, messageMap);
            messageUserMap.put(chat_user_ref+"/"+push_id,messageMap);

            textChat.setText("");

            mUserRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if(databaseError != null){
                        Log.d("Chat_Log", databaseError.getMessage().toString());
                    }
                }
            });


        }
    }
}
















