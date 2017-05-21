package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    private DatabaseReference mDatabaseCurrentUser;

    private Query mQuery;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String role = "Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){

                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);

                } else {

                    String currentUserId = mAuth.getCurrentUser().getUid();


                    //mQuery = mDatabase.orderByChild("").equalTo(currentUserId);

                }

            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");





    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);


    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
