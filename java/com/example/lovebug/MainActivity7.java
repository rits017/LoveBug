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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity7 extends AppCompatActivity {
      TextView textView5,textView6,textView7,textView10,textView11,textView12,textView13,textView14,textView15,textView16;
      Button button12,button13,button14,button15,button16,button17,button18,button19,button20,button21,button22;
      ImageButton imageButton2,imageButton4;
      ImageView imageView4;
      public String this_uid;
   public String take_url;
   public static final String IMG_URL = "uurl";
   public static final String PP_1 = "dds";
      DatabaseReference zDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18= findViewById(R.id.button18);
        button19= findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21= findViewById(R.id.button21);
        button22= findViewById(R.id.button22);
        imageButton2= findViewById(R.id.imageButton2);
        imageButton4 = findViewById(R.id.imageButton4);
        imageView4= findViewById(R.id.imageView4);
        Intent thisintent = getIntent();
        this_uid = thisintent.getStringExtra(MainActivity4.GIV_UID);
        zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
        zDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String set_user_id = snapshot.child(this_uid).child("Userid").getValue().toString();
                String set_user_name = snapshot.child(this_uid).child("Username").getValue().toString();
                String set_user_age = snapshot.child(this_uid).child("Userage").getValue().toString();
                String set_user_profile = snapshot.child(this_uid).child("Userimageurl").getValue().toString();
                take_url = set_user_profile;
                String take_gender = snapshot.child(this_uid).child("Usergender").getValue().toString();
                  textView5.setText(set_user_id);
                  textView6.setText(set_user_name);
                  textView7.setText(set_user_age);
                          if(!set_user_profile.equals("")) {
                              Glide.with(MainActivity7.this).load(set_user_profile).into(imageView4);
                          }
                if(set_user_profile.equals("") && take_gender.equalsIgnoreCase("Female")){
                    Glide.with(MainActivity7.this).load("https://www.shutterstock.com/image-vector/illustration-women-long-hair-style-600w-530848897.jpg").into(imageView4);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(take_url.equals("")){
                    Toast.makeText(MainActivity7.this,"You don't have a profile pic !",Toast.LENGTH_SHORT).show();
                }
                Intent iiintent = new Intent(MainActivity7.this,MainActivity10.class);
                startActivity(iiintent);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent humint = new Intent(MainActivity7.this,MainActivity9.class);
                startActivity(humint);
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingt = new Intent(MainActivity7.this,MainActivity15.class);
                startActivity(ingt);
            }
        });
          button12.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                  zDatabase.child(this_uid).child("Userq1").setValue("YES");
                  Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
              }
          });
          button13.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                  zDatabase.child(this_uid).child("Userq1").setValue("NO");
                  Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
              }
          });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq2").setValue("SERIOUS");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq2").setValue("CASUAL");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq3").setValue("INTROVERT");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq3").setValue("EXTROVERT");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq4").setValue("FRIENDSHIP");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq4").setValue("DATING");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq5").setValue("SINGLE");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.child(this_uid).child("Userq5").setValue("TAKEN");
                Toast.makeText(MainActivity7.this,"Saving your Reply !",Toast.LENGTH_SHORT).show();
            }
        });
          button22.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent inn = new Intent(MainActivity7.this,MainActivity14.class);
                  startActivity(inn);
              }
          });
    }
}