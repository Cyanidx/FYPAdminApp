package com.example.jerald.fypadminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddGate extends AppCompatActivity {

    EditText etGate;
    Spinner spnTerminal;
    Button btnAdd;
    ArrayList<String> terminalArray = new ArrayList<>();
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseSpinner;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gate);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate");
        mDatabaseSpinner = FirebaseDatabase.getInstance().getReference();

        etGate = (EditText)findViewById(R.id.etGate);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        spnTerminal = (Spinner)findViewById(R.id.spinner2);

        mDatabaseSpinner.child("Terminal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final ArrayList<String> terminal = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {

                    String terminalName = areaSnapshot.child("terminalName").getValue(String.class);
                    terminal.add(terminalName);


                }

                Spinner terminalSpinner = (Spinner) findViewById(R.id.spinner2);

                ArrayAdapter<String> terminalAdapter = new ArrayAdapter<String>(AddGate.this, android.R.layout.simple_spinner_item, terminal);

                terminalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                terminalSpinner.setAdapter(terminalAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

        final String gate = etGate.getText().toString();
        final String terminal = spnTerminal.getSelectedItem().toString();

        if(gate.isEmpty()){
            Toast.makeText(AddGate.this, "Empty Gate Field", Toast.LENGTH_SHORT).show();
        } else {
            final DatabaseReference newPost = mDatabase.child(gate);
            //String id = newPost.getKey();



            newPost.child("gateName").setValue(gate);
            newPost.child("terminalName").setValue(terminal);
            //newPost.child("id").setValue(id);

            Toast.makeText(AddGate.this, "Gate Added", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(AddGate.this, ManageGate.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
        }

    }


}
