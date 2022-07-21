package com.beyza.bilcat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    //public User user = new User("Dilara Kıymaz","dilarakiymz@gmail.com","02122130462");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //TODO:This is going to come from the sign in database
        //updateProfile(user.getNametxt(), user.getEmailtxt(), user.getPhonetxt());

        TextView fullName1 = findViewById(R.id.nameSurnameTextView);
        TextView email1 = findViewById(R.id.emailTextView);
        TextView phoneNumber1 = findViewById(R.id.phoneNumberTextView);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                phoneNumber1.setText(documentSnapshot.getString("phone"));
                fullName1.setText(documentSnapshot.getString("fname"));
                email1.setText(documentSnapshot.getString("email"));
            }
        });

        Button btnHomePage = findViewById(R.id.homePageButton);
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainActivity();
            }
        });
    }

    public void updateProfile(String fullName, String email, String phoneNumber){
       /*TextView fullName1 = findViewById(R.id.nameSurnameTextView);
        TextView email1 = findViewById(R.id.emailTextView);
        TextView phoneNumber1 = findViewById(R.id.phoneNumberTextView);

        fullName1.setText(fullName);
        email1.setText(email);
        phoneNumber1.setText(phoneNumber);
        */
    }

    public void displayEditProfile(View view){
        //Intent intent = new Intent(this, EditProfile.class);
        //startActivity(intent);
    }

    public void goMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}