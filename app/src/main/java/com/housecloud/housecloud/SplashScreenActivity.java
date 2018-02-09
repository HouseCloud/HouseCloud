package com.housecloud.housecloud;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView img;
    //TextView txvHouse, txvCloud;

    private static int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        img = (ImageView)findViewById(R.id.imglogo);
        //txvHouse = (TextView)findViewById(R.id.txvHouse);
        //txvCloud = (TextView)findViewById(R.id.txvCloud);

        //Typeface fuente = Typeface.createFromAsset(getAssets(),"KGOneMoreNight.ttf");
        Animation translateLogo = AnimationUtils.loadAnimation(this,R.anim.translate_logo);
        //Animation translateHC = AnimationUtils.loadAnimation(this,R.anim.translate_housecloud);

        //txvHouse.setTypeface(fuente);
        //txvCloud.setTypeface(fuente);
        img.startAnimation(translateLogo);
        //txvHouse.startAnimation(translateHC);
        //txvCloud.startAnimation(translateHC);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(i);
            }
        },SPLASH_TIME_OUT);
    }
}
