package com.housecloud.housecloud.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.housecloud.housecloud.R;
import com.housecloud.housecloud.model.Categoria;

import java.util.ArrayList;

/**
 * Created by Jozet on 12/03/2018.
 */

public class CategoriaAdapter extends ArrayAdapter<Categoria> {

    public CategoriaAdapter(Context context, ArrayList<Categoria> categoriaList) {
        super(context, 0,categoriaList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_categoria,parent,false);
        }

        ImageView imgCategoria = convertView.findViewById(R.id.imgCategoria);
        TextView tvCategoria = convertView.findViewById(R.id.tvCategoria);

        Categoria categoria = getItem(position);

        imgCategoria.setImageResource(categoria.getIconoC());
        tvCategoria.setText(categoria.getCategoria());

        return convertView;
    }
}
