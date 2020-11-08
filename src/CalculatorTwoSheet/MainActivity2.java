package com.example.appgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button2=(Button) findViewById(R.id.button2);


    }

    public void onButtonClick(View v){
        //create edit text with id Num1 and  Num2
        //also and text view
        EditText num1 =(EditText)findViewById(R.id.Num1);
        EditText num2 =(EditText)findViewById(R.id.Num2);
        TextView restext=(TextView)findViewById(R.id.Result);

        //from String to  Integer
        int number_of_object1 = Integer.parseInt(num1.getText().toString());
        int number_of_object2 = Integer.parseInt(num2.getText().toString());
        int ResSum = number_of_object1+number_of_object2;
        //send Text in TextView
        restext.setText(Integer.toString(ResSum));
    }
}