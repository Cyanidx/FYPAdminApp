package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingleDateActivity extends AppCompatActivity {

    TextView tvDate;
    Button btnDelete, btnManageFlight;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_date);

        tvDate = (TextView)findViewById(R.id.tvDate);
        btnDelete = (Button)findViewById(R.id.btnDeleteDate);
        btnManageFlight = (Button)findViewById(R.id.btnManageFlight);

        Intent i = this.getIntent();
        final String date = i.getStringExtra("date");
        final String gateID = i.getStringExtra("gateID");

        tvDate.setText(date);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("TimeSlot");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(date).removeValue();
                Intent mainIntent = new Intent(SingleDateActivity.this, ManageTimeSlot.class);
                mainIntent.putExtra("gateID",gateID);
                startActivity(mainIntent);

            }
        });

        btnManageFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(SingleDateActivity.this, ManageFlight.class);
                Intent.putExtra("gateID",gateID);
                Intent.putExtra("date",date);
                startActivity(Intent);
            }
        });

    }
}
