package com.beyza.bilcat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize firebase auth
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        //login button action
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        TextView registerNowBtn = findViewById(R.id.registerNowBtn);
        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });

    }
    private void authenticateUser(){
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();
        if ( emailtxt.isEmpty() || passwordtxt.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show();
            return;
        }
        //if user registered correctly using email and password we add data of the user to database
        mAuth.signInWithEmailAndPassword(emailtxt,passwordtxt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            showMainActivity();
                        } else{
                            Toast.makeText(Login.this,"authentication failed",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
    //Switch to main activity
    private void showMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void switchToRegister(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
        finish();
    }
}