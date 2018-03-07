package com.housecloud.housecloud;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Button bt;
    RelativeLayout rl;

    RecyclerView rvMain; //Instaciamos el recycleView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rvMain = (RecyclerView)findViewById(R.id.rvMain);
        bt = (Button) findViewById(R.id.menu);
        rl = (RelativeLayout) findViewById(R.id.vista);

        Bitmap[] bitmaps = getBitmaps();
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(bitmaps);
        rvMain.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvMain.setAdapter(myRecyclerAdapter);


    }

    public void accion(View v) {
        rl.animate().translationX(400);
        if(rl.getX()==0){
            //setContentView(R.layout.activity_main2);
            //Intent i = new Intent(MainActivity.this, Main2Activity.class);
            //startActivity(i);
            rl.animate().translationX(400);
        }else{
            rl.animate().translationX(0);
        }

    }

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

    private class MyRecyclerAdapter extends  RecyclerView.Adapter<GridHolder>{

        Bitmap[] bitmaps;

        public MyRecyclerAdapter(Bitmap[] bitmaps) {
            this.bitmaps = bitmaps;
        }

        @Override
        public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MenuActivity.this).inflate(R.layout.cards_main, parent, false);
            return new GridHolder(view);
        }

        @Override
        public void onBindViewHolder(GridHolder holder, int position) {
            holder.imageView.setImageBitmap(bitmaps[position]);
            if(position%2!=0){
                //holder.imageView.setVisibility(View.INVISIBLE);
                holder.itemView.setTranslationY(350+50);
            }else{
                holder.itemView.setTranslationY(50);
            }

            Log.d("myTag", position+"");
            holder.textView.setText("Caption: "+position);
        }

        @Override
        public int getItemCount() {
            return bitmaps.length;
        }
    }

    private class GridHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public GridHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivMainImage);
            textView = itemView.findViewById(R.id.tvCaption);
        }
    }
}
