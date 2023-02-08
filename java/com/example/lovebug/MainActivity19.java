package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class MainActivity19 extends AppCompatActivity {
          RecyclerView rv191;
          ImageView i191;
          TextView t191,t192;
          public String my_un;
          public String his_un;
          private DatabaseReference zoo;
          private DatabaseReference zee;
          public String user_id_p;
          ArrayList<String> last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        rv191 = findViewById(R.id.rv191);
        rv191.setLayoutManager(new LinearLayoutManager(MainActivity19.this));
        i191 = findViewById(R.id.i191);
        t191 = findViewById(R.id.t191);
        t192 = findViewById(R.id.t192);
        Intent deck = getIntent();
        my_un = deck.getStringExtra(MainActivity18.MYCHILD);
        his_un = deck.getStringExtra(MainActivity18.HECHILD);
        last = new ArrayList<>();
        CustomAdapter3 dsa = new CustomAdapter3(last);
        zoo = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
        zoo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String img_url = snapshot.child(his_un).child("Userimageurl").getValue().toString();
                if(!img_url.equals("")) {
                    Glide.with(MainActivity19.this).load(img_url).into(i191);
                }
                String name= snapshot.child(his_un).child("Username").getValue().toString();
                String gen = snapshot.child(his_un).child("Usergender").getValue().toString();
                t191.setText(name + " , " + gen);
                String id = snapshot.child(his_un).child("Userid").getValue().toString();
                user_id_p = id;
                String age = snapshot.child(his_un).child("Userage").getValue().toString();
                t192.setText(id + " , " + age);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
         zee = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
         zee.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 String last_texts = snapshot.child(my_un).child("Message").child(user_id_p).getValue().toString();
                 if(!last_texts.equals("")) {
                     last.add("Last text received :- " + last_texts);
                     rv191.setAdapter(dsa);
                 }
                 else{
                     last.add("No text received !");
                     rv191.setAdapter(dsa);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
    }
}