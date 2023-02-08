package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity16 extends AppCompatActivity {
         RecyclerView recyclerView2;
         FirebaseAuth mauth;
         Button b161,b162;
         ImageButton i161;
         EditText t161;
         ArrayList<String> send;
         ArrayList<String> rec;
         public String user_text_id;
         public String user_pass;
         public String taken_phone;
         private DatabaseReference lDatabase;
         private DatabaseReference koiDatabase;
         private DatabaseReference datas;
         private DatabaseReference qDatabase;
         public String unique;
         public static final String AUTH= "sds";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity16.this));
        i161= findViewById(R.id.i161);
        b161 = findViewById(R.id.b161);

        t161 = findViewById(R.id.t161);
        mauth = FirebaseAuth.getInstance();
        unique = mauth.getCurrentUser().getUid();
        Intent dekhlo = getIntent();
        user_pass = dekhlo.getStringExtra(MainActivity15.PHONE);
        taken_phone = dekhlo.getStringExtra(MainActivity15.USER_NUMBER);
        send = new ArrayList<>();
        rec = new ArrayList<>();
         CustomAdapter2 ada = new CustomAdapter2(send);
        Toast.makeText(MainActivity16.this, "Remember,all texts will get automatically deleted once you click back !", Toast.LENGTH_SHORT).show();
        qDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
        qDatabase.child(unique).child("Message").child(taken_phone).setValue("");
        qDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 user_text_id = snapshot.child(unique).child("Userid").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        i161.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = t161.getText().toString();
                lDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                lDatabase.child(user_pass).child("Message").child(user_text_id).setValue(text);
                send.add("Me :- "+text);
                recyclerView2.setAdapter(ada);
                t161.setText("");
            }
        });

        b161.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent koko = new Intent(MainActivity16.this,MainActivity20.class);
             koko.putExtra(AUTH,user_pass);
             startActivity(koko);
            }
        });
         datas = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
         datas.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {

                 String rec_text = snapshot.child(unique).child("Message").child(taken_phone).getValue().toString();
                 if(!rec_text.equals("")){
                     send.add("Rec :- " + rec_text);
                     recyclerView2.setAdapter(ada);
                     datas.child(unique).child("Message").child(taken_phone).setValue("");
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

    }
}