package com.example.huggywuggy;

import static android.graphics.Color.BLACK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.Random;

public class StartActivity extends AppCompatActivity {

    boolean readyTrigger = false;

    int minR = 0;   // 0
    int maxR = 4;   // 5

    Random r = new Random();
    private boolean gameOver = false;

    @Override
    public void onBackPressed() {
        // your code.
        //Popup popup
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Animation shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);   // load animations to be used during the application run
        final Animation fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        final Animation slowfadeinAnimation = AnimationUtils.loadAnimation(this, R.anim.slowfade_in);
        final Animation slowerfadeinAnimation = AnimationUtils.loadAnimation(this, R.anim.slowerfade_in);
        final Animation slowfadeoutAnimation = AnimationUtils.loadAnimation(this, R.anim.slowfade_out);
        final Animation slowerfadeoutAnimation = AnimationUtils.loadAnimation(this, R.anim.slowerfade_out);
        final Animation dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        final Animation fastblinkAnimation = AnimationUtils.loadAnimation(this, R.anim.fastblinking);
        final Animation scaleupAnimation = AnimationUtils.loadAnimation(this, R.anim.scaleup);
        final Animation blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blinking);


        MediaPlayer squeakytoySound = MediaPlayer.create(StartActivity.this, R.raw.squeakytoy);          // cannot be before this else it will crash
        MediaPlayer transformscarySound = MediaPlayer.create(StartActivity.this, R.raw.transformscary);
        MediaPlayer jumpscareSound = MediaPlayer.create(StartActivity.this, R.raw.jumpscare);

        MediaPlayer switchtoySound = MediaPlayer.create(StartActivity.this, R.raw.lightoff);
        MediaPlayer transformcreakSound = MediaPlayer.create(StartActivity.this, R.raw.floorcreek);
        MediaPlayer jumpscreamSound = MediaPlayer.create(StartActivity.this, R.raw.femalescream);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView _lowLight = (ImageView) findViewById(R.id.lowLight);
        ImageView _overlayLight = (ImageView) findViewById(R.id.overlayLight);
        ImageButton _hugButton = (ImageButton) findViewById(R.id.hugButton);
        ImageButton _toyStart = (ImageButton) findViewById(R.id.toyStart);
        ImageButton _restartButton = (ImageButton) findViewById(R.id.restartButton);
        ImageButton _toyTeeth = (ImageButton) findViewById(R.id.toyTeeth);
        ImageButton _toyHighlight = (ImageButton) findViewById(R.id.toyHighlight);

        PopupMenu backMenu = new PopupMenu(this, _hugButton);
        backMenu.inflate(R.menu.popup_menu);

        _restartButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(StartActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slowfade_in, R.anim.slowfade_out);
            }
        });

        _toyStart.postDelayed(new Runnable() {
            @Override
            public void run() {
                _toyStart.setVisibility(View.VISIBLE);
                _toyStart.setEnabled(false);
                _toyStart.startAnimation(dropAnimation);


                _toyHighlight.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //_toyHighlight.setVisibility(View.VISIBLE);
                        _toyHighlight.startAnimation(blinkAnimation);


                    }
                }, 500);
                _toyStart.setEnabled(true);
            }
        }, 2000);

        _toyHighlight.clearAnimation();
        _toyHighlight.setVisibility(View.INVISIBLE);

        _overlayLight.postDelayed(new Runnable() {
            @Override
            public void run() {
                _overlayLight.setVisibility(View.VISIBLE);
                _overlayLight.startAnimation(slowfadeinAnimation);
                _hugButton.setVisibility(View.VISIBLE);
                _hugButton.startAnimation(slowfadeinAnimation);
            }
        }, 1300);

        _toyStart.setOnClickListener(new View.OnClickListener() {                                // when huggy wuggy is pressed
            @Override
            public void onClick(View view) {

                _toyStart.setEnabled(false);                                                     // START OF DELAY BUTTON
                _toyStart.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (gameOver)
                        {

                        }
                        else
                        {

                            _toyStart.setEnabled(true);
                        }
                    }
                }, 1050);                                                                        // END OF DELAY BUTTON


                int rand = r.nextInt(maxR - minR + 1) + minR;
                _toyStart.startAnimation(shakeAnimation);
                _hugButton.startAnimation(fadeAnimation);
                //Toast.makeText(getApplicationContext(), ""+rand+"", Toast.LENGTH_SHORT).show();       // debug notification of roll
                squeakytoySound.start();

                if (rand == 0) {
                    if (readyTrigger) {
                        gameOver = true;
                        int altend = r.nextInt(2);

                        if (altend == 0) {  // new ending

                            Log.d("altend","roll was 0 -> "+altend);
                            _hugButton.clearAnimation();
                            _hugButton.setEnabled(false);
                            _toyStart.setEnabled(false);
                            switchtoySound.start();
                            transformcreakSound.start();
                            _lowLight.setVisibility(View.GONE);
                            _overlayLight.startAnimation(slowerfadeoutAnimation);
                            _toyStart.startAnimation(slowerfadeoutAnimation);
                            _hugButton.clearAnimation();
                            _hugButton.setVisibility(View.GONE);

                            _toyTeeth.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    _restartButton.setVisibility(View.VISIBLE);
                                    _restartButton.startAnimation(slowerfadeinAnimation);
                                    _toyStart.clearAnimation();
                                    _overlayLight.clearAnimation();
                                    //_toyStart.setVisibility(View.GONE);
                                    _toyStart.startAnimation(scaleupAnimation);
                                    _overlayLight.setVisibility(View.GONE);
                                    jumpscreamSound.start();
                                    _toyTeeth.setVisibility(View.VISIBLE);
                                    _toyTeeth.startAnimation(scaleupAnimation);


                                }
                            }, 2000);

                            _toyStart.setVisibility(View.GONE);

                        }
                        else    // run old ending
                        {
                            Log.d("altend","roll was 1 -> "+altend);

                            _lowLight.setVisibility(View.GONE);
                            _overlayLight.setVisibility(View.GONE);
                            _hugButton.setEnabled(false);
                            _toyStart.setEnabled(false);
                            _hugButton.clearAnimation();
                            _hugButton.setVisibility(View.GONE);
                            _toyStart.setImageResource(R.drawable.hwlost);
                            _toyStart.setBackgroundColor(Color.rgb(0, 0, 0));
                            jumpscareSound.start();
                            _restartButton.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    _restartButton.setVisibility(View.VISIBLE);
                                    _restartButton.startAnimation(slowerfadeinAnimation);


                                }
                            }, 2000);

                        }
                    }
                    else
                    {
                        _toyStart.setImageResource(R.drawable.hwteeth);
                        transformscarySound.start();
                        _toyStart.startAnimation(fastblinkAnimation);
                        _overlayLight.startAnimation(fastblinkAnimation);
                        readyTrigger = true;
                    }
                }
            }
        });


    }
}