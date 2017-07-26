package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageFlight2 extends AppCompatActivity {

    private RecyclerView mFlightList;

    Spinner spnDate;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_flight2);

        spnDate = (Spinner) findViewById(R.id.spnDate);

        Intent i = this.getIntent();
        //String date = i.getStringExtra("date");
        String gateID = i.getStringExtra("gateName");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("DaySlot");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> date = new ArrayList<String>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {

                    String x = areaSnapshot.child("date").getValue(String.class);
                    date.add(x);


                }
                Spinner spnDate = (Spinner) findViewById(R.id.spnDate);

                ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(ManageFlight2.this, android.R.layout.simple_spinner_item, date);

                dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnDate.setAdapter(dateAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spnDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = spnDate.getItemAtPosition(position).toString();
                find(choice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFlightList = (RecyclerView)findViewById(R.id.flight_list);
        mFlightList.setHasFixedSize(true);
        mFlightList.setLayoutManager(new LinearLayoutManager(this));
        mFlightList.getRecycledViewPool().clear();



    }
    public void find(String choice) {

        FirebaseRecyclerAdapter<TimeSlot, FlightViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TimeSlot, FlightViewHolder>(

                TimeSlot.class,
                R.layout.timerow,
                FlightViewHolder.class,
                mDatabase.child(choice).child("Flight")


        ) {
            @Override
            protected void populateViewHolder(FlightViewHolder viewHolder, final TimeSlot model, int position) {

                //final String post_key = getRef(position).getKey();

                viewHolder.setDate("Date: " + model.getDate());
                viewHolder.setTime("Time: " + model.getTime());
                viewHolder.setPlane("Plane ID: " + model.getPlaneID());
                viewHolder.setFlight("Flight Number: " + model.getFlightNo());
                viewHolder.setDirection("Direction: " + model.getDirection());
                if(model.getDirection().equals("Not Updated")){
                    viewHolder.mView.setBackgroundColor(Color.RED);
                } else {
                    viewHolder.mView.setBackgroundColor(Color.GREEN);
                }


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent x = new Intent(ManageFlight2.this, TimeSingleActivity.class);
                        x.putExtra("date", model.getDate());
                        x.putExtra("time", model.getTime());
                        x.putExtra("flight", model.getFlightNo());
                        x.putExtra("plane", model.getPlaneID());
                        x.putExtra("gateID", model.getGateID());
                        startActivity(x);

                    }
                });

            }
        };

        mFlightList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();


    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public FlightViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDate(String title){

            TextView date = (TextView)mView.findViewById(R.id.tvDate);
            date.setText(title);

        }

        public void setTime(String desc){

            TextView time = (TextView)mView.findViewById(R.id.tvTime);
            time.setText(desc);

        }

        public void setPlane(String x){

            TextView plane = (TextView)mView.findViewById(R.id.tvPlane);
            plane.setText(x);

        }

        public void setFlight(String x){

            TextView flight = (TextView)mView.findViewById(R.id.tvFlight);
            flight.setText(x);

        }

        public void setDirection(String x){

            TextView direction = (TextView)mView.findViewById(R.id.tvDirection);
            direction.setText(x);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        if(item.getItemId() == R.id.action_add_flight){

            Intent a = new Intent(ManageFlight2.this, AddTimeSlot.class);
            startActivity(a);

        }



        return super.onOptionsItemSelected(item);
    }
}
