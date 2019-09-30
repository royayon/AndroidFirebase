package com.ayon.androidfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText name,age,desc;
    Button btnInsert,btnData;

    DatabaseReference dbRef;

    Info info;

    long lastSerial = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        desc = findViewById(R.id.desc);
        btnInsert = findViewById(R.id.btnInsert);
        btnData = findViewById(R.id.btnData);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Info");

        info = new Info();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    lastSerial = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString().trim();
                String Age = age.getText().toString().trim();
                String Desc = desc.getText().toString().trim();

                info.setName(Name);
                info.setAge(Age);
                info.setDesc(Desc);

                dbRef.child(String.valueOf(lastSerial+1)).setValue(info);
                Toast.makeText(getApplicationContext(),"Stored in Database!",Toast.LENGTH_SHORT).show();
            }
        });

        //Datas
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RecyclerData.class);
                startActivity(i);
            }
        });

    }
}
