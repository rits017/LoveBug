package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity20 extends AppCompatActivity {
        ImageView i201,i202;
        TextView t201,t202;
        private DatabaseReference take;
        public String unidd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        i201 = findViewById(R.id.i201);
        i202 = findViewById(R.id.i202);
        t201 = findViewById(R.id.t201);
        t202 = findViewById(R.id.t202);
        Intent neck = getIntent();
        unidd = neck.getStringExtra(MainActivity16.AUTH);
        take = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
        take.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             String imageurl = snapshot.child(unidd).child("Userimageurl").getValue().toString();
             if(!imageurl.equals("")){
                 Glide.with(MainActivity20.this).load(imageurl).into(i201);
             }
                String name= snapshot.child(unidd).child("Username").getValue().toString();
                String gen = snapshot.child(unidd).child("Usergender").getValue().toString();
                t201.setText(name + " , " + gen);
                String id = snapshot.child(unidd).child("Userid").getValue().toString();
                String age = snapshot.child(unidd).child("Userage").getValue().toString();
                t202.setText(id + " , " + age);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}