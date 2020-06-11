package com.facturasmanzanillo.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView clouds;
    ImageView imageButtonSound;
    boolean sound = true;
    MediaPlayer ambient;


    public void onClick2x3(View view){
        Intent intent = new Intent(this, Main2x3Activity.class);
        startActivity(intent);
        if (sound){
            ambient.stop();
            ambient.release();
            imageButtonSound.setImageResource(R.drawable.silence);
            sound = false;
        }
    }

    public void toggleSound(View view){
        if (sound) {
            imageButtonSound.setImageResource(R.drawable.silence);
            sound = false;
            ambient.stop();
            ambient.release();

        } else {
            imageButtonSound.setImageResource(R.drawable.sonido);
            sound = true;
            ambient = MediaPlayer.create(getApplicationContext(), R.raw.funshine);
            ambient.start();
        }
    }

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
        setContentView(R.layout.activity_main);

        clouds = findViewById(R.id.clouds);
        imageButtonSound = findViewById(R.id.imageButtonSound);
        moveClouds();

        ambient = MediaPlayer.create(getApplicationContext(), R.raw.funshine);
        ambient.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ambient.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ambient.start();
    }
}
