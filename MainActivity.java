package com.example.ashutosh.eggcountdown;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView timerDisplay;
    SeekBar setTimerSeekBar;
    boolean timerRunning= false;
    CountDownTimer countDownTimer;
    Button button;


    public void resetTimer(){

        button = findViewById(R.id.button);
        button.setText("GO!!");
        setTimerSeekBar.setEnabled(true);
        setTimerSeekBar.setProgress(30);
        timerRunning = false;
        countDownTimer.cancel();
        timerDisplay.setText("0:30");
    }



    public void updateText(int timeInSeconds){
        int minutes = timeInSeconds/60;
        int seconds = timeInSeconds%60;

        String Minutes = Integer.toString(minutes);
        String Seconds = Integer.toString(seconds);
        if (seconds < 10){
            Seconds = "0" + Seconds;
        }
        timerDisplay = findViewById(R.id.timeDisplay);
        timerDisplay.setText(Minutes + ":" + Seconds);

    }

    public void onClick(View view) {
        if (timerRunning == false) {
            button = findViewById(R.id.button);
            button.setText("STOP!");
            timerRunning = true;
            setTimerSeekBar.setEnabled(false);


            countDownTimer = new CountDownTimer(setTimerSeekBar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long milliSecondsUntilLeft) {

                    updateText((int) milliSecondsUntilLeft / 1000);

                }

                public void onFinish() {
                    updateText(0);
                    Toast.makeText(getApplicationContext(), "Timer Finished", Toast.LENGTH_SHORT).show();
                    resetTimer();
                }
            }.start();

        }
        else{
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTimerSeekBar = (SeekBar) findViewById(R.id.setTimerSeekBar);
        setTimerSeekBar.setMax(600);
        setTimerSeekBar.setProgress(30);

        setTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateText(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
