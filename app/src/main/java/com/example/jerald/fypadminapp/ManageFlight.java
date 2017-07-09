package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;

public class ManageFlight extends AppCompatActivity {

    ListView lvFlight;

    DatabaseReference mDatabase;
    ArrayList<TimeSlot> times = new ArrayList<>();
    TimeSlotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flight);

        lvFlight = (ListView)findViewById(R.id.lvFlight);

        Intent i = this.getIntent();
        String date = i.getStringExtra("date");
        String gateID = i.getStringExtra("gateID");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("DaySlot").child(date).child("Flight");

        adapter = new TimeSlotAdapter(this, retrieve());
        lvFlight.setAdapter(adapter);

    }

    public ArrayList<TimeSlot> retrieve(){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { // is already a Date object
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TimeSlot time = ds.getValue(TimeSlot.class);
                    times.add(time);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return times;

    }

}
