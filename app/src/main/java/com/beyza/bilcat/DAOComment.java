package com.beyza.bilcat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOComment {

    private DatabaseReference databaseReference;

    public DAOComment() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(CommentData.class.getSimpleName());
    }

    public Task<Void> add(CommentData commentData) {
        return databaseReference.push().setValue(commentData);
    }

}
