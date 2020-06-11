package com.facturasmanzanillo.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

public class Main2x3Activity extends AppCompatActivity {

    MediaPlayer ukelele;
    boolean sound = true;
    ImageView clouds;

    public void moveClouds(){
        ObjectAnimator animatorX;
        animatorX = ObjectAnimator.ofFloat(clouds, "x", 1000f, -800f);
        animatorX.setDuration(40000);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX);
        animatorSet.start();
        new CountDownTimer(900000,40010){
            @Override
            public void onTick(long millisUntilFinished) {
                animatorSet.start();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2x3);

        clouds = findViewById(R.id.clouds);

        ukelele = MediaPlayer.create(getApplicationContext(), R.raw.ukulelesong);
        ukelele.start();
        moveClouds();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ukelele.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
            ukelele.start();
    }
}
