package com.example.jerald.fypadminapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 15017292 on 25/7/2017.
 */

public class ManageUserFragment extends Fragment {
    private RecyclerView mUserList;

    DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manage_user,container,false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mUserList = (RecyclerView)view.findViewById(R.id.user_list);
        mUserList.setHasFixedSize(true);
        mUserList.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<User, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(

                User.class,
                R.layout.userrow,
                UserViewHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, final User model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setRole(model.getRole());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //alert for confirm to delete
                        builder.setMessage("Delete?");    //set message

                        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabase.child(post_key).removeValue();

                                return;
                            }
                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                return;
                            }
                        }).show();  //show alert dialog



                    }
                });

            }
        };



        mUserList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public UserViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setName(String title){

            TextView post_title = (TextView)mView.findViewById(R.id.name);
            post_title.setText(title);

        }

        public void setRole(String desc){

            TextView post_desc = (TextView)mView.findViewById(R.id.role);
            post_desc.setText(desc);

        }


    }
}
