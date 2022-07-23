package com.beyza.bilcat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class EditProfile extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        changePasswordButton = findViewById(R.id.changePasswordButton);

        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        final EditText oldPasswordText = findViewById(R.id.editTextOldPassword);
        final EditText newPasswordText = findViewById(R.id.editTextNewPassword);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = oldPasswordText.getText().toString().trim();
                String newPassword = newPasswordText.getText().toString().trim();

                if(TextUtils.isEmpty(oldPassword)){
                    Toast.makeText(EditProfile.this, "Enter your current password...", Toast.LENGTH_LONG).show();
                    return;
                }

                if(newPassword.length() < 5){
                    Toast.makeText(EditProfile.this, "Password length must be at least 5 characters.", Toast.LENGTH_LONG).show();
                    return;
                }

                updatePassword(oldPassword, newPassword);
                goProfile();

            }
        });

        Button homeButton = findViewById(R.id.belowHomePageButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainActivity();
            }
        });

        /*Button catsButton = findViewById(R.id.belowCatsButton);
        catsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCatList();
            }
        });*/

        /*Button mapButton = findViewById(R.id.belowMapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMap();
            }
        });*/

        Button profileButton = findViewById(R.id.belowProfileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goProfile();
            }
        });




    }

    private void updatePassword(String oldPassword, String newPassword) {

        FirebaseUser user = fAuth.getCurrentUser();
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);
        user.reauthenticate(authCredential)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        user.updatePassword(newPassword)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(EditProfile.this, "Password updated...",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(EditProfile.this, "" + e.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, "" + e.getMessage(),Toast.LENGTH_LONG).show();
                        return;
                    }
                });
    }

    public void goMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*public void goCatList(){
        Intent intent = new Intent(this, CatList.class);
        startActivity(intent);
    }*/

    /*public void goMap(){
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }*/

    public void goProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}