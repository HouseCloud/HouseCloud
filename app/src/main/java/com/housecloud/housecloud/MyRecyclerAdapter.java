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

/**
 * Created by alberto on 26/2/18.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.GridHolder> {

    Bitmap[] bitmaps;

    public MyRecyclerAdapter(Bitmap[] bitmaps) {
        this.bitmaps = bitmaps;
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_main, parent, false);
        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {
        holder.imageView.setImageBitmap(bitmaps[position]);
        /*if(position%2!=0){
            //holder.imageView.setVisibility(View.INVISIBLE);
            holder.itemView.setTranslationY(350+50);
        }else{
            holder.itemView.setTranslationY(50);
        }*/

        Log.d("myTag", position+"");
        holder.textView.setText("Caption: "+position);
    }

    @Override
    public int getItemCount() {
        return bitmaps.length;
    }

    public class GridHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public GridHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivMainImage);
            textView = itemView.findViewById(R.id.tvCaption);
        }
    }
}
