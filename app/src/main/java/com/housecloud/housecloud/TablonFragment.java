package com.housecloud.housecloud;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class TablonFragment extends Fragment {

    Button bt;
    RelativeLayout rl;


    RecyclerView rvMain2; //Instaciamos el recycleView

    public TablonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tablon, container, false);

        rvMain2 = (RecyclerView)v.findViewById(R.id.rvMain2);
        Bitmap[] bitmaps = getBitmaps();
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(bitmaps);
        rvMain2.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvMain2.setAdapter(myRecyclerAdapter);

        return v;
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


}
