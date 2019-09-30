package com.ayon.androidfirebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseAdapter11 {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private List<Info> infos = new ArrayList<>();

    public FirebaseAdapter11() {
        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference().child("Info");
    }

    public void readDB() {
        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infos.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Info info = keyNode.getValue(Info.class);
                    infos.add(info);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
