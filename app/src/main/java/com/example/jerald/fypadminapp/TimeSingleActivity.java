package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class TimeSingleActivity extends AppCompatActivity {

    EditText etDate,etTime,etFlight,etPlane;
    Button btnEdit, btnDelete;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_single);

        etDate = (EditText)findViewById(R.id.etDate);
        etTime = (EditText)findViewById(R.id.etTime);
        etFlight = (EditText)findViewById(R.id.etFlight);
        etPlane = (EditText)findViewById(R.id.etPlane);

        Intent i = this.getIntent();
        String date = i.getStringExtra("date");
        String time = i.getStringExtra("time");
        String flight = i.getStringExtra("flight");
        String plane = i.getStringExtra("plane");
        final String id = i.getStringExtra("id");

        etDate.setText(date);
        etTime.setText(time);
        etFlight.setText(flight);
        etPlane.setText(plane);




    }
}
