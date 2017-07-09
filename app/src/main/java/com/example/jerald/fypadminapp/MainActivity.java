package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private Button btnMG, btnMU, btnAddTerminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMG = (Button)findViewById(R.id.btnManageGate);
        btnMU = (Button)findViewById(R.id.btnManageUser);
        btnAddTerminal = (Button)findViewById(R.id.btnAddTerminal);
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

        btnMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ManageGateIntent = new Intent(MainActivity.this, ManageGate.class);
                ManageGateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ManageGateIntent);

            }
        });

        btnMU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ManageUserIntent = new Intent(MainActivity.this, ManageUser.class);
                ManageUserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ManageUserIntent);
            }
        });

        btnAddTerminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ManageUserIntent = new Intent(MainActivity.this, AddTerminal.class);
                ManageUserIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ManageUserIntent);
            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.action_logout){

            logout();

        }


        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
}
