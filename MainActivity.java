package com.example.huggywuggy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Random r = new Random();
    int minR = 0;
    int maxR = 3;

    @Override
    public void onBackPressed() {
        // your code.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Animation animBlink = AnimationUtils.loadAnimation(this, R.anim.blinking);

        MediaPlayer startTitle = MediaPlayer.create(MainActivity.this, R.raw.maintitlescary);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTitle.start();

        ImageView mainLight = (ImageView) findViewById(R.id.startBackground);
        ImageButton startButton = (ImageButton) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set the button's appearance
                startButton.setSelected(!startButton.isSelected());

                if (startButton.isSelected()) {

                    startTitle.stop();
                    Intent i = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slowfade_in, R.anim.slowfade_out);
                    //Handle selected state change
                }
                else {
                    //Handle de-select state change
                }

                //startTitle.stop();
                //Intent i = new Intent(MainActivity.this, StartActivity.class);
                //startActivity(i);
                //overridePendingTransition(R.anim.slowfade_in, R.anim.slowfade_out);
                //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                //startActivity(new Intent(MainActivity.this, StartActivity.class));

                //startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });

        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {                                                         // https://stackoverflow.com/questions/5161951/android-only-the-original-thread-that-created-a-view-hierarchy-can-touch-its-vi
              @Override
              public void run() {

                  runOnUiThread(new Runnable() {

                  @Override
                  public void run() {
                      int rand = r.nextInt(maxR - minR + 1) + minR;

                      mainLight.setVisibility(View.INVISIBLE);
                      if (rand == 0) {

                          mainLight.setVisibility(View.VISIBLE);
                          mainLight.startAnimation(animBlink);
                      }

                  }
              });
              }

          }, 0, 500);

    }
}