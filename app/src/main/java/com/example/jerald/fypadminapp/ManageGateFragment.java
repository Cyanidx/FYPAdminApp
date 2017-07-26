package com.example.jerald.fypadminapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.id.list;

/**
 * Created by 15017292 on 23/7/2017.
 */

public class ManageGateFragment extends Fragment {
    private RecyclerView mGateList;

    DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manage_gate2,container,false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gate");

        mGateList = (RecyclerView)view.findViewById(R.id.gate_list);
        mGateList.setHasFixedSize(true);
        mGateList.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<Gate, GateViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Gate, GateViewHolder>(

                Gate.class,
                R.layout.gate_row,
                GateViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(GateViewHolder viewHolder, final Gate model, int position) {

                //final String post_key = getRef(position).getKey();

                viewHolder.setTerminal(model.getTerminalName());
                viewHolder.setGate(model.getGateName());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //alert for confirm to delete
                        builder.setMessage("View Manage Date or Delete?");    //set message

                        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabase.child(model.getGateName()).removeValue();

                                return;
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                return;
                            }
                        }).setNeutralButton("Manage Date", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent x = new Intent(getActivity(), ManageFlight2.class);
                                x.putExtra("gateName",model.getGateName());
                                x.putExtra("terminalName",model.getTerminalName());
                                startActivity(x);
                                return;
                            }
                        }).show();  //show alert dialog



                    }
                });

            }
        };



        mGateList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class GateViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public GateViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setTerminal(String title){

            TextView post_title = (TextView)mView.findViewById(R.id.terminal);
            post_title.setText(title);

        }

        public void setGate(String desc){

            TextView post_desc = (TextView)mView.findViewById(R.id.gate);
            post_desc.setText(desc);

        }


    }

}
