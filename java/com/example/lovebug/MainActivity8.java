package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity8 extends AppCompatActivity {
    TextView textView21,textView22,textView23,textView24,textView25,textView26,textView27,textView28,textView29,textView30;
    Button button120,button130,button140,button150,button160,button170,button180,button190,button200,button210,button220;
    ImageButton imageButton20,imageButton40;
    ImageView imageView20;
    public String that_uid;
     public static final String IMG_URL_2="uuu";
    public String take_url_2;
     FirebaseAuth getAuth;
    DatabaseReference yDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        textView28 = findViewById(R.id.textView28);
        textView29 = findViewById(R.id.textView29);
        textView30 = findViewById(R.id.textView30);
        button120 = findViewById(R.id.button120);
        button130 = findViewById(R.id.button130);
        button140 = findViewById(R.id.button140);
        button150 = findViewById(R.id.button150);
        button160 = findViewById(R.id.button160);
        button170 = findViewById(R.id.button170);
        button180= findViewById(R.id.button180);
        button190= findViewById(R.id.button190);
        button200 = findViewById(R.id.button200);
        button210= findViewById(R.id.button210);
        button220= findViewById(R.id.button220);
        imageButton20= findViewById(R.id.imageButton20);
        imageButton40 = findViewById(R.id.imageButton40);
        imageView20= findViewById(R.id.imageView20);
        getAuth = FirebaseAuth.getInstance();
        that_uid = getAuth.getCurrentUser().getUid();

        yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
        yDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String set_user_id = snapshot.child(that_uid).child("Userid").getValue().toString();
                String set_user_name = snapshot.child(that_uid).child("Username").getValue().toString();
                String set_user_age = snapshot.child(that_uid).child("Userage").getValue().toString();
                String set_user_profile = snapshot.child(that_uid).child("Userimageurl").getValue().toString();
                take_url_2 = set_user_profile;
                String take_gender = snapshot.child(that_uid).child("Usergender").getValue().toString();
                textView21.setText(set_user_id);
                textView22.setText(set_user_name);
                textView23.setText(set_user_age);
                if(!set_user_profile.equals("")) {
                    Glide.with(MainActivity8.this).load(set_user_profile).into(imageView20);
                }
                if(set_user_profile.equals("") && take_gender.equalsIgnoreCase("Female")){
                    Glide.with(MainActivity8.this).load("https://www.shutterstock.com/image-vector/illustration-women-long-hair-style-600w-530848897.jpg").into(imageView20);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imageView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(take_url_2.equals("")){
                    Toast.makeText(MainActivity8.this,"You don't have a profile pic !",Toast.LENGTH_SHORT).show();
                }
                Intent inintent = new Intent(MainActivity8.this,MainActivity10.class);
                startActivity(inintent);

            }
        });
        imageButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tumint = new Intent(MainActivity8.this,MainActivity9.class);
                startActivity(tumint);
            }
        });
        imageButton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt = new Intent(MainActivity8.this,MainActivity15.class);
                startActivity(intt);
            }
        });
        button120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq1").setValue("YES");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq1").setValue("NO");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button140.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq2").setValue("SERIOUS");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq2").setValue("CASUAL");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button160.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq3").setValue("INTROVERT");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button170.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq3").setValue("EXTROVERT");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq4").setValue("FRIENDSHIP");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button190.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq4").setValue("DATING");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
         yDatabase.child(that_uid).child("Userq5").setValue("SINGLE");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button210.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                yDatabase.child(that_uid).child("Userq5").setValue("TAKEN");
                Toast.makeText(MainActivity8.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button220.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onn = new Intent(MainActivity8.this,MainActivity14.class);
                startActivity(onn);
            }
        });
    }
    }
