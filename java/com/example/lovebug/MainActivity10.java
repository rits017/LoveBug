package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity10 extends AppCompatActivity {
     ImageView i101;

     public String im1 = "";
     private DatabaseReference mydata;
     FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        i101 = findViewById(R.id.i101);
        mauth = FirebaseAuth.getInstance();

        String imp = mauth.getCurrentUser().getUid();
        mydata = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/").child(imp);
        mydata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String  image_url = snapshot.child("Userimageurl").getValue().toString();
                if(!image_url.equals("")){
                    Toast.makeText(MainActivity10.this,"Opening your profile !",Toast.LENGTH_SHORT).show();
                    Glide.with(MainActivity10.this).load(image_url).into(i101);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}