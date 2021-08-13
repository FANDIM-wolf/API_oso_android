package com.example.testlungs;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("name").toString();    // get name from another intent
        String second_name = arguments.get("second_name").toString();
        String record_of_user  = arguments.get("record_of_user").toString();
        // get object of text view
        final TextView text_name = (TextView)findViewById(R.id.textViewNameOfUser);
        final TextView text_second_name = (TextView)findViewById(R.id.textViewNameTwo);
        final TextView text_record = (TextView)findViewById(R.id.textViewResult);
        text_name.setText(name);
        text_second_name.setText(second_name);
        text_record.setText(record_of_user);
    }
}