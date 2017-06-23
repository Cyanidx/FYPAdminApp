package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageUser extends AppCompatActivity {

    FloatingActionButton fab;
    ListView lvUser;

    DatabaseReference mDatabase;
    ArrayList<User> users = new ArrayList<>();
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        lvUser = (ListView)findViewById(R.id.lvUser);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageUser.this, AddUser.class));
            }
        });

        adapter = new UserAdapter(this, retrieve());
        lvUser.setAdapter(adapter);

    }

    public ArrayList<User> retrieve(){

        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    users.add(user);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return users;

    }

}
