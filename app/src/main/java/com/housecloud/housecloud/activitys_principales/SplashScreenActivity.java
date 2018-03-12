package com.housecloud.housecloud.activitys_principales;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.housecloud.housecloud.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView img;
    VideoView vd;
    //TextView txvHouse, txvCloud;

    private static int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        vd = (VideoView)findViewById(R.id.videoSplash);

        Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.screensplash55);

        vd.setVideoURI(video);

        vd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                if (isFinishing())
                    return;

                Intent i = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        vd.start();


        /*img = (ImageView)findViewById(R.id.imglogo);
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
                Intent i = new Intent(SplashScreenActivity.this,LoginActivity0.class);
                startActivity(i);
            }
        },SPLASH_TIME_OUT);*/


    }
}
