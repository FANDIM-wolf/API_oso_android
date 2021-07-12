package com.example.headfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }
    //Called when sendSMS
    public void sendSMS(View view){
        EditText messageView = (EditText)findViewById(R.id.message);
        String messageText = messageView.getText().toString();
        //intent to send message
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,messageText);
        startActivity(intent);
    }

}