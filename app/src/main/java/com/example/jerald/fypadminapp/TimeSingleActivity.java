package com.example.jerald.fypadminapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeSingleActivity extends AppCompatActivity {

    EditText etDate,etTime,etFlight,etPlane;
    Button btnEdit, btnDelete;
    TextView tvDate;

    DatabaseReference mDatabase;

    Calendar myCalendar = Calendar.getInstance();
    int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
    int minute = myCalendar.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_single);

        tvDate = (TextView)findViewById(R.id.tvDate);
        etTime = (EditText)findViewById(R.id.etTime);
        etFlight = (EditText)findViewById(R.id.etFlight);
        etPlane = (EditText)findViewById(R.id.etPlane);
        btnEdit = (Button)findViewById(R.id.btnEditTimeSlot);
        btnDelete = (Button)findViewById(R.id.btnDeleteTimeSlot);



        Intent i = this.getIntent();
        final String date = i.getStringExtra("date");
        final String time = i.getStringExtra("time");
        String flight = i.getStringExtra("flight");
        String plane = i.getStringExtra("plane");
        final String gateID = i.getStringExtra("gateID");

        setTitle(gateID+" - Edit Flight");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("DaySlot").child(date).child("Flight");

        tvDate.setText(date);
        etTime.setText(time);
        etFlight.setText(flight);
        etPlane.setText(plane);

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(TimeSingleActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                etTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(time).child("flightNo").setValue(etFlight.getText().toString());
                mDatabase.child(time).child("time").setValue(etTime.getText().toString());
                mDatabase.child(time).child("planeID").setValue(etPlane.getText().toString());

                Toast.makeText(TimeSingleActivity.this, "Time Slot Edited", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(TimeSingleActivity.this, ManageFlight2.class);
                a.putExtra("gateName",gateID);
                startActivity(a);


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(time).removeValue();
                Toast.makeText(TimeSingleActivity.this, "Time Slot Deleted", Toast.LENGTH_SHORT).show();
                Intent x = new Intent(TimeSingleActivity.this, ManageFlight2.class);
                x.putExtra("gateName",gateID);
                startActivity(x);
            }
        });




    }
}
