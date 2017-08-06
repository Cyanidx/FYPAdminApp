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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTimeSlot extends AppCompatActivity {

    EditText etDate, etTime, etFlight, etPlane;
    private DatabaseReference mDatabase;

    Button btnAdd;

    Calendar myCalendar = Calendar.getInstance();
    int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
    int minute = myCalendar.get(Calendar.MINUTE);

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_slot);

        etDate = (EditText)findViewById(R.id.etDate);
        etTime = (EditText)findViewById(R.id.etTime);
        etFlight = (EditText)findViewById(R.id.etFlight);
        etPlane = (EditText)findViewById(R.id.etPlane);
        btnAdd = (Button)findViewById(R.id.btnAddTimeSlot);


        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddTimeSlot.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddTimeSlot.this,
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

    }

    private void Add(){

        String date = etDate.getText().toString();
        String time = etTime.getText().toString();
        String flight = etFlight.getText().toString();
        String plane = etPlane.getText().toString();

        if(date.isEmpty()){
            Toast.makeText(AddTimeSlot.this, "Please in fill all the empty field", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = this.getIntent();
            String gateID = i.getStringExtra("gateID");

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("DaySlot");
            DatabaseReference newDate = mDatabase.child(date);
            newDate.child("date").setValue(date);
            newDate.child("gateID").setValue(gateID);
            DatabaseReference newTime = newDate.child("Flight");
            DatabaseReference newPost = newTime.child(time);


            newPost.child("date").setValue(date);
            newPost.child("time").setValue(time);
            newPost.child("flightNo").setValue(flight);
            newPost.child("planeID").setValue(plane);
            newPost.child("gateID").setValue(gateID);
            newPost.child("direction").setValue("Not Updated");

            Toast.makeText(AddTimeSlot.this, "Flight Added", Toast.LENGTH_SHORT).show();
            Intent z = new Intent(AddTimeSlot.this, ManageFlight2.class);
            z.putExtra("gateName",gateID);
            z.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(z);
        }

    }
    private void updateLabel() {
        String myFormat = "dd-M-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }
}
