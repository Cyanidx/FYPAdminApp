package com.example.jerald.fypadminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTerminal extends AppCompatActivity {

    EditText etTerminal;
    Button btnAddTerminal;

    private FirebaseAuth mAuth;

    private FirebaseUser mCurrentUser;

    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terminal);

        mAuth = FirebaseAuth.getInstance();

        mCurrentUser = mAuth.getCurrentUser();

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Terminal");

        etTerminal = (EditText)findViewById(R.id.etTerminal);
        btnAddTerminal = (Button)findViewById(R.id.btnAddTerminal);

        mProgress = new ProgressDialog(this);

        btnAddTerminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTerminal();
            }
        });


    }

    private void addTerminal(){



        final String terminal = etTerminal.getText().toString().trim();

        if(terminal.isEmpty()){

            Toast.makeText(AddTerminal.this, "Empty Terminal Field", Toast.LENGTH_SHORT).show();

        } else {
            final DatabaseReference newPost = mDatabase.push();

            newPost.child("terminalName").setValue(terminal);
            Toast.makeText(AddTerminal.this, "Terminal Added", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(AddTerminal.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }




    }

}
