package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GateSingleActivity extends AppCompatActivity {

    Button btnEdit;
    Button btnDelete;
    TextView tvGate;
    TextView tvTerminal;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_single);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate");

        tvGate = (TextView) findViewById(R.id.tvGate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        tvTerminal = (TextView) findViewById(R.id.tvTerminal);

        Intent i = this.getIntent();
        String name = i.getStringExtra("gateName");
        String terminal = i.getStringExtra("terminalName");
        final String id = i.getStringExtra("id");

        tvGate.setText(name);
        tvTerminal.setText(terminal);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(id).removeValue();
                Intent mainIntent = new Intent(GateSingleActivity.this, ManageGate.class);
                startActivity(mainIntent);

            }
        });






    }
}
