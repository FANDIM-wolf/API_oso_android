package com.example.testlungs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private int seconds = 0; // consists seconds
    private boolean running; // current state of stopwatch
    private boolean wasRunning; //save state , before we turn horizontally
    private boolean acceleration = false;
    private String  record; // save record to transfer it to another intent

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
                    record = time; //save time for intent
                }
                if( running && acceleration == true ){
                    seconds = seconds +2;
                    record = time; //save time for intent
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
        //create object of EditText to get name and second name of user
        EditText input_name = (EditText) findViewById(R.id.input_one);
        EditText input_second_name = (EditText) findViewById(R.id.input_two);
        Editable name = input_name.getText();
        Editable second_name =input_second_name.getText();

        // создание объекта Intent для запуска SecondActivity
        Intent intent = new Intent(this, MainActivity2.class);
// передача объекта с ключом "hello" и значением "Hello World"
        intent.putExtra("name ", name);
        intent.putExtra("second_name",second_name);
        intent.putExtra("record_of_user" , record);

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