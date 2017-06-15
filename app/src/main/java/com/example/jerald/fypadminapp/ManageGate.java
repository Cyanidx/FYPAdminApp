package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class ManageGate extends AppCompatActivity {

    FloatingActionButton fab;
    ListView lvGate;

    DatabaseReference mDatabase;
    ArrayList<Gate> gates = new ArrayList<>();
    GateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_gate);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        lvGate = (ListView)findViewById(R.id.lvGate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageGate.this, AddGate.class));
            }
        });


        adapter = new GateAdapter(this, retrieve());
        lvGate.setAdapter(adapter);



    }


    public ArrayList<Gate> retrieve(){

        mDatabase.child("Gate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Gate gate = ds.getValue(Gate.class);
                    gates.add(gate);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return gates;

    }



}
