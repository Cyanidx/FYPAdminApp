package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    private DatabaseReference mDatabaseCurrentUser;

    private Query mQuery;

    ArrayList<User> users = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

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
                    mDatabaseCurrentUser = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
                    retrieve();


                }

            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ManageGateFragment(), "Manage Gate");
        adapter.addFragment(new ManageUserFragment(), "Manage User");
        viewPager.setAdapter(adapter);
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

        if(item.getItemId() == R.id.action_add_gate){

            Intent a = new Intent(MainActivity.this, AddGate.class);
            startActivity(a);

        }
        if(item.getItemId() == R.id.action_add_terminal){

            Intent a = new Intent(MainActivity.this, AddTerminal.class);
            startActivity(a);

        }

        if(item.getItemId() == R.id.action_add_user){

            Intent a = new Intent(MainActivity.this, AddUser.class);
            startActivity(a);

        }


        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
    public ArrayList<User> retrieve(){

        mDatabaseCurrentUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                if(user.getRole().equals("Admin")){
                    Toast.makeText(MainActivity.this, "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error your account is not eligible to login", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return users;

    }
}
