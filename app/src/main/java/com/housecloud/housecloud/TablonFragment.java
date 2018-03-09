package com.housecloud.housecloud;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.housecloud.housecloud.model.Post;
import com.housecloud.housecloud.model.User;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TablonFragment extends Fragment {

    Button bt;
    RelativeLayout rl;
    private ArrayList<Post> publicaciones;
    //private ArrayList<User> users;
    private FirebaseDatabase refUsers;
    private MyRecyclerAdapter myRecyclerAdapter;

    RecyclerView rvMain2; //Instaciamos el recycleView

    public TablonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tablon, container, false);


        publicaciones = new ArrayList<Post>();
        //users = new ArrayList<User>();

        rvMain2 = (RecyclerView)v.findViewById(R.id.rvMain2);
        recogerDatosFirebase();
        rvMain2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        myRecyclerAdapter = new MyRecyclerAdapter(getContext(), publicaciones);

        myRecyclerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"TOAST",Toast.LENGTH_LONG).show();
                Post p = publicaciones.get(rvMain2.getChildAdapterPosition(view));
                Intent i = new Intent(getContext(), ChatActivity.class);
                i.putExtra("user_id",p.getId_user());
                startActivity(i);
            }
        });

        rvMain2.setAdapter(myRecyclerAdapter);

        return v;
    }

    private void recogerDatosFirebase() {

        refUsers = FirebaseDatabase.getInstance();
        refUsers.getReference().getRoot().child("users").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                publicaciones.removeAll(publicaciones);
                for(DataSnapshot snapshotUsers : dataSnapshot.getChildren()){
                        User user = snapshotUsers.getValue(User.class);
                        //users.add(user);
                        HashMap<String,Post> listP = user.getPosts();
                        try {
                            for(Post p  : listP.values()){
                                publicaciones.add(p);
                            }
                        }catch(Exception e){

                        }

                }
                myRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*
    private Bitmap[] getBitmaps() {

        Bitmap[] tempBitmaps = new Bitmap[8];
        tempBitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_1);
        tempBitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_2);
        tempBitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_3);
        tempBitmaps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_4);
        tempBitmaps[4] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_5);
        tempBitmaps[5] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_3);
        tempBitmaps[6] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_4);
        tempBitmaps[7] = BitmapFactory.decodeResource(getResources(), R.drawable.icon_5);

        return tempBitmaps;
    }
    */

}
