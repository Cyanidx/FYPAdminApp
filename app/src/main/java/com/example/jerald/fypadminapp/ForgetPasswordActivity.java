package com.example.jerald.fypadminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mEmail;
    private Button mForgetBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText)findViewById(R.id.forgetEmail);
        mForgetBtn = (Button)findViewById(R.id.forgetBtn);

        mForgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send();


            }
        });

    }
    public void send(){
        String email = mEmail.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(this, "Email field must not be empty", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.sendPasswordResetEmail(email);
            Intent i = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(i);
        }


    }
}
