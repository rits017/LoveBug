package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity12 extends AppCompatActivity {
     ImageView i121;
     TextView t121;
     Button b121;
     EditText e121,e122,e123;
     private DatabaseReference thisBase;
     private DatabaseReference setting;
     public String tocheck;
     public String lelouid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        i121 = findViewById(R.id.i121);
        t121 = findViewById(R.id.t121);
        e121 = findViewById(R.id.e121);
        e122 = findViewById(R.id.e122);
        e123 = findViewById(R.id.e123);
        b121 = findViewById(R.id.b121);

        Intent seek = getIntent();
        lelouid = seek.getStringExtra(MainActivity9.DEKH_UID);
        setting = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/").child(lelouid);
          setting.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  tocheck = snapshot.child("Userpassword").getValue().toString();
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
        b121.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass_1 = e121.getText().toString();
                String pass_2 = e122.getText().toString();
                String pass_3 = e123.getText().toString();
                if (pass_3.isEmpty() || pass_1.isEmpty() || pass_2.isEmpty()) {
                    Toast.makeText(MainActivity12.this, "Enter all the required fields !", Toast.LENGTH_SHORT).show();
                }
                else {
                     if(pass_1.equals(pass_2)){

                         thisBase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");

                         if(pass_3.equals(tocheck)){
                         thisBase.child(lelouid).child("Userpassword").setValue(pass_1);
                         Toast.makeText(MainActivity12.this, "Password changed successfully !", Toast.LENGTH_SHORT).show();
                         Intent tabi = new Intent(MainActivity12.this,MainActivity9.class);
                         startActivity(tabi);
                     }
                         else{
                             Toast.makeText(MainActivity12.this, "Wrong Current Password !", Toast.LENGTH_SHORT).show();
                         }
                     }
                   else{
                         Toast.makeText(MainActivity12.this,"Both the fields are not matching !", Toast.LENGTH_SHORT).show();
                     }
                }
            }

        });

    }
}