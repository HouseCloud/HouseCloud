package com.housecloud.housecloud;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.housecloud.housecloud.model.Post;
import com.housecloud.housecloud.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by alberto on 26/2/18.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.GridHolder> implements View.OnClickListener{

    private ArrayList<Post> listaP;
    //private ArrayList<User> listaU;
    private View.OnClickListener listener;
    private Context context;

    public MyRecyclerAdapter(Context context, ArrayList<Post> listaP) {
        this.listaP = listaP;
        this.context = context;
       // this.listaU = listaU;
    }

    private DatabaseReference mDatabase;

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_main, parent, false);
        view.setOnClickListener(this);
        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(final GridHolder holder, int position) {




        Post p = listaP.get(position);
        //User u = listaU.get(position);

        holder.tvTitulo.setText(p.getTitulo());
        holder.tvDescripcion.setText(p.getDescripcion());

        holder.tvCategoria.setText(p.getCategoria());

        if(p.getCategoria().equalsIgnoreCase("Herramientas")){
            holder.tvCategoria.setBackgroundResource(R.drawable.categorias_amarillo);
        }else if(p.getCategoria().equalsIgnoreCase("Juguetes")){
            holder.tvCategoria.setBackgroundResource(R.drawable.categorias_azul);
        }else if(p.getCategoria().equalsIgnoreCase("Electronica")){
            holder.tvCategoria.setBackgroundResource(R.drawable.categorias_morado);
        }else{
            holder.tvCategoria.setBackgroundResource(R.drawable.categorias_verde);
        }




        //holder.tvNombre.setText(u.getName());
        /*if(position%2!=0){
            //holder.imageView.setVisibility(View.INVISIBLE);
            holder.itemView.setTranslationY(100);
        }else{
            holder.itemView.setTranslationY(0);
        }*/

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(p.getId_user()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                holder.tvNombre.setText(user.getName());

                Glide.with(context).load(user.getImage()).into(holder.mFoto);
                Log.d("VERIFICAR", "User name: " + user.getName()+ ", email " + user.getImage());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("VERIFICAR", "Failed to read value");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaP.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class GridHolder extends RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvTitulo;
        TextView tvDescripcion;
        TextView tvCategoria;
        CircleImageView mFoto;



        public GridHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreUser);
            tvTitulo = itemView.findViewById(R.id.tvTituloPost);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionPost);
            tvCategoria = itemView.findViewById(R.id.tvCategoriaPost);
            mFoto = itemView.findViewById(R.id.userImgTablon);

        }

        public TextView getTvNombre() {
            return tvNombre;
        }

        public TextView getTvTitulo() {
            return tvTitulo;
        }

        public TextView getTvDescripcion() {
            return tvDescripcion;
        }

        public TextView getTvCategoria() {
            return tvCategoria;
        }
    }
}
