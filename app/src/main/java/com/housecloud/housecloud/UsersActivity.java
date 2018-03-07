package com.housecloud.housecloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsersActivity extends AppCompatActivity {

    private  RecyclerView mUsersLists;
    private DatabaseReference mUsersDatabase;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mUsersLists = (RecyclerView) findViewById(R.id.users_list);
        mUsersLists.setHasFixedSize(true);
        mUsersLists.setLayoutManager(new LinearLayoutManager(this));

        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        mUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());

        if(currentUser!=null){
            mUserRef.child("online").setValue("true");
        }
    }

    @Override
    protected void onStart() {
        mUserRef.child("online").setValue("true");
        super.onStart();

        FirebaseRecyclerAdapter<Users, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UserViewHolder>(
                Users.class,
                R.layout.users_sigle_layout,
                UserViewHolder.class,
                mUsersDatabase
        ) {
            @Override
            protected void populateViewHolder(final UserViewHolder userViewHolder, Users users, int position) {
                userViewHolder.setName(users.getName());

                final String list_user_id = getRef(position).getKey();

                mUsersDatabase.child(list_user_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        userViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(userViewHolder.mView.getContext(), ChatActivity.class);
                                i.putExtra("user_id",list_user_id);
                                startActivity(i);
                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        };

        mUsersLists.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        //mUserRef.child("online").setValue("false");
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public UserViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name){
            TextView userNameView = mView.findViewById(R.id.user_single_name);
            userNameView.setText(name);
        }

    }


}
