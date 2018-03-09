package com.housecloud.housecloud;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.housecloud.housecloud.model.Post;
import com.housecloud.housecloud.model.User;

import java.util.ArrayList;

/**
 * Created by alberto on 26/2/18.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.GridHolder>
                implements View.OnClickListener{

    private ArrayList<Post> listaP;
    //private ArrayList<User> listaU;
    private View.OnClickListener listener;

    public MyRecyclerAdapter(ArrayList<Post> listaP) {
        this.listaP = listaP;
       // this.listaU = listaU;
    }



    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_main, parent, false);
        view.setOnClickListener(this);
        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {
        Post p = listaP.get(position);
        //User u = listaU.get(position);

        holder.tvTitulo.setText(p.getTitulo());
        holder.tvDescripcion.setText(p.getDescripcion());
        holder.tvCategoria.setText(p.getCategoria());
        //holder.tvNombre.setText(u.getName());
        /*if(position%2!=0){
            //holder.imageView.setVisibility(View.INVISIBLE);
            holder.itemView.setTranslationY(350+50);
        }else{
            holder.itemView.setTranslationY(50);
        }*/

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

        public GridHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreUser);
            tvTitulo = itemView.findViewById(R.id.tvTituloPost);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionPost);
            tvCategoria = itemView.findViewById(R.id.tvCategoriaPost);
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
