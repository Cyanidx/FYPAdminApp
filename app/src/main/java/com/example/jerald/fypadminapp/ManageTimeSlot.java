package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageTimeSlot extends AppCompatActivity {

    FloatingActionButton fab;
    ListView lvTime;

    DatabaseReference mDatabase;
    ArrayList<TimeSlot> times = new ArrayList<>();
    TimeSlotAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_time_slot);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        lvTime = (ListView)findViewById(R.id.lvTimeSlot);



        Intent i = this.getIntent();
        final String gateID = i.getStringExtra("gateID");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("TimeSlot");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(ManageTimeSlot.this, AddTimeSlot.class);
                Intent.putExtra("gateID",gateID);
                startActivity(Intent);
            }
        });

        adapter = new TimeSlotAdapter(this, retrieve());
        lvTime.setAdapter(adapter);



    }
    public ArrayList<TimeSlot> retrieve(){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
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
