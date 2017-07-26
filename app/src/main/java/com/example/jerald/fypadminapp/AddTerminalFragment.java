package com.example.jerald.fypadminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 15017292 on 23/7/2017.
 */

public class AddTerminalFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    EditText etTerminal;
    Button btnAddTerminal;

    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_terminal,container,false);mDatabase = FirebaseDatabase.getInstance().getReference().child("Terminal");

        etTerminal = (EditText)view.findViewById(R.id.etTerminal);
        btnAddTerminal = (Button)view.findViewById(R.id.btnAddTerminal);

        btnAddTerminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTerminal();
            }
        });

        return view;
    }
    private void addTerminal(){



        final String terminal = etTerminal.getText().toString().trim();

        if(terminal.isEmpty()){

            Toast.makeText(getActivity(), "Empty Terminal Field", Toast.LENGTH_SHORT).show();

        } else {
            final DatabaseReference newPost = mDatabase.push();

            newPost.child("terminalName").setValue(terminal);
            Toast.makeText(getActivity(), "Terminal Added", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }


    }
}
