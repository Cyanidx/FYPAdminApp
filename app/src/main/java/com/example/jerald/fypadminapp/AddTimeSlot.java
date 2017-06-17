package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTimeSlot extends AppCompatActivity {

    EditText etDate, etTime, etFlight, etPlane;
    private DatabaseReference mDatabase;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_slot);

        etDate = (EditText)findViewById(R.id.etDate);
        etTime = (EditText)findViewById(R.id.etTime);
        etFlight = (EditText)findViewById(R.id.etFlight);
        etPlane = (EditText)findViewById(R.id.etPlane);
        btnAdd = (Button)findViewById(R.id.btnAddTimeSlot);

        Intent i = this.getIntent();
        final String id = i.getStringExtra("id");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(id).child("TimeSlot");

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
            final DatabaseReference newPost = mDatabase.push();
            String id = newPost.getKey();

            newPost.child("date").setValue(date);
            newPost.child("time").setValue(time);
            newPost.child("flightNo").setValue(flight);
            newPost.child("planeID").setValue(plane);
            newPost.child("id").setValue(id);
            newPost.child("direction").setValue("Not Updated");

            Toast.makeText(AddTimeSlot.this, "Flight Added", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(AddTimeSlot.this, ManageTimeSlot.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
        }

    }
}
