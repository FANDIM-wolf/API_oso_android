package com.example.testlungs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private int seconds = 0; // consists seconds
    private boolean running; // current state of stopwatch
    private boolean wasRunning; //save state , before we turn horizontally
    private boolean acceleration = false;


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
                        String time = String.format(Locale.getDefault(),"%d:%02d:%02d:%05d",hours,minutes,secs,milosecs);
                timeView.setText(time);
                if(running && acceleration == false){
                    seconds++;
                }
                if( running && acceleration == true ){
                    seconds = seconds +2;
                }
                handler.postDelayed(this,1000);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch aSwitch =  (Switch) findViewById(R.id.switch1);
        final TextView text_switch = (TextView)findViewById(R.id.textView1);
        aSwitch.setOnCheckedChangeListener(this);
        runStopWatch();
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

    }

    //save seconds when user turn phone horizontally
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }



    //start the stopwatch , when this button is clicked
    public void  Start_stopwatch(View view){
        running = true;
        super.onStart();
        if(wasRunning){
         }

    }
    //stop the stopwatch , when this button is clicked
    public void  Stop_stopwatch(View view){
        running = false;
        // создание объекта Intent для запуска SecondActivity
        Intent intent = new Intent(this, MainActivity2.class);
// передача объекта с ключом "hello" и значением "Hello World"
        intent.putExtra("hello", "Hello World");
        intent.putExtra("name","Mikhail");

// запуск SecondActivity
        startActivity(intent);
    }
    //reset the stopwatch , when this button is clicked
    public void  Reset_stopwatch(View view){

        running = false;
        seconds = 0 ;

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            showMessage("ON");
            acceleration = true;
        }
        else{
            showMessage("OFF");
            acceleration = false;
        }
    }

    private  void  showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}