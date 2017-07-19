package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        btnEdit = (Button)findViewById(R.id.btnEditTimeSlot);
        btnDelete = (Button)findViewById(R.id.btnDeleteTimeSlot);



        Intent i = this.getIntent();
        String date = i.getStringExtra("date");
        final String time = i.getStringExtra("time");
        String flight = i.getStringExtra("flight");
        String plane = i.getStringExtra("plane");
        final String gateID = i.getStringExtra("gateID");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("DaySlot").child(date).child("Flight");

        etDate.setText(date);
        etTime.setText(time);
        etFlight.setText(flight);
        etPlane.setText(plane);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(time).child("flightNo").setValue(etFlight.getText().toString());
                mDatabase.child(time).child("date").setValue(etDate.getText().toString());
                mDatabase.child(time).child("time").setValue(etTime.getText().toString());
                mDatabase.child(time).child("planeID").setValue(etPlane.getText().toString());

                Toast.makeText(TimeSingleActivity.this, "Time Slot Edited", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(TimeSingleActivity.this, ManageTimeSlot.class);
                a.putExtra("gateID",gateID);
                startActivity(a);


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(time).removeValue();
                Toast.makeText(TimeSingleActivity.this, "Time Slot Deleted", Toast.LENGTH_SHORT).show();
                Intent x = new Intent(TimeSingleActivity.this, ManageTimeSlot.class);
                x.putExtra("gateID",gateID);
                startActivity(x);
            }
        });




    }
}
