package com.ayon.androidfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerData extends AppCompatActivity {

    DatabaseReference dbReff;
    RecyclerView myRecyclerView;
    ArrayList<Info> list;
    FirebaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_data);

        myRecyclerView = findViewById(R.id.myRecycler);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Info>();


        dbReff = FirebaseDatabase.getInstance().getReference().child("Info");
        dbReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Info i = dataSnapshot1.getValue(Info.class);
                    list.add(i);
                }
                adapter = new FirebaseAdapter(getApplicationContext(),list);
                myRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"OOPs...!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
