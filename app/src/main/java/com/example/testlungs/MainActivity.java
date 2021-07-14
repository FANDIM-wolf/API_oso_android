package com.example.testlungs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0; // consists seconds
    private boolean running; // current state of stopwatch

    //common function of stopwatch
    private void runStopWatch(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        //use Handler class to run code which changes time immediately
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs  =seconds%60;
                int milosecs = seconds/1000;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d:%04d",hours,minutes,secs,milosecs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runStopWatch();

    }

    //start the stopwatch , when this button is clicked
    public void  Start_stopwatch(View view){
        running = true;

    }
    //stop the stopwatch , when this button is clicked
    public void  Stop_stopwatch(View view){
        running = false;

    }
    //reset the stopwatch , when this button is clicked
    public void  Reset_stopwatch(View view){

        running = false;
        seconds = 0 ;

    }

}