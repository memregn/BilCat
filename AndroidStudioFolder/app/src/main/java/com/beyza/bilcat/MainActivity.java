package com.beyza.bilcat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize firebase auth
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        // id user is not entered show login page
        if (currentUser == null){
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
            finish();
            return;
        }

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        Button profileButton = findViewById(R.id.myProfileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               displayProfile();
            }
        });

        Button catListButton= findViewById(R.id.catListButton);
        catListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCatList();
            }
        });

        Button generalMapButton = findViewById(R.id.generalMapButton);
        generalMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGeneralMap();
            }
        });

    }

    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }

    public void displayGeneralMap(){
        // mapClicked and pingClicked set to false to display the GeneralMap
        CatList.pingClicked=false;
        CatList.mapClicked=false;
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    private void displayCatList(){
        Intent intent = new Intent(this, CatList.class);
        startActivity(intent);
    }

    private void displayProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

}