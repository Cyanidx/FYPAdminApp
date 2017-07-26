package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class ManageDate extends AppCompatActivity {

    private RecyclerView mDateList;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_date);

        Intent i = this.getIntent();
        final String gateID = i.getStringExtra("gateName");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate").child(gateID).child("DaySlot");

        mDateList = (RecyclerView)findViewById(R.id.date_list);
        mDateList.setHasFixedSize(true);
        mDateList.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Date, DateViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Date, DateViewHolder>(

                Date.class,
                R.layout.daterow,
                DateViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(DateViewHolder viewHolder, final Date model, int position) {

                //final String post_key = getRef(position).getKey();

                viewHolder.setDate(model.getDate());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent x = new Intent(ManageDate.this, ManageFlight2.class);
                        x.putExtra("date",model.getDate());
                        x.putExtra("gateID",model.getGateID());
                        startActivity(x);

                    }
                });

            }
        };



        mDateList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class DateViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public DateViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDate(String date){

            TextView Pdate = (TextView)mView.findViewById(R.id.date);
            Pdate.setText(date);

        }

    }

}
