package com.beyza.bilcat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize firebase auth
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        //register
        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        //switch to login
        TextView loginNowBtn = findViewById(R.id.loginNowBtn);
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swithToLogin();
            }
        });

    }
    private void  registerUser(){
        EditText fullname = findViewById(R.id.fullname);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        EditText password = findViewById(R.id.password);

        String nametxt = fullname.getText().toString();
        String emailtxt = email.getText().toString();
        String phonetxt = phone.getText().toString();
        String passwordtxt = password.getText().toString();

        if (nametxt.isEmpty() || emailtxt.isEmpty() || phonetxt.isEmpty() || passwordtxt.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT);
            return;
        }

        //if user registered correctly using email and password we add data of the user to database
        mAuth.createUserWithEmailAndPassword(emailtxt,passwordtxt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this,"user created",Toast.LENGTH_SHORT);
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname",nametxt);
                            user.put("email",emailtxt);
                            user.put("phone",phonetxt);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"on succes : user  profile" + userID);

                                }
                            });
                            showMainActivity();


                            //use the user class to create a user

                            //User user = new User(nametxt,phonetxt,emailtxt);

                            //save the data of the user to database

                            /*FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                //close register and open main activity
                                public void onComplete(@NonNull Task<Void> task) {
                                    showMainActivity();
                                }
                            });*/
                        } else{
                            Toast.makeText(Register.this,"authentication failed",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    //move to the other class (main)
    private void showMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void swithToLogin(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }
}