package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    public static final String PHONE = "MyNum";
    ImageView iv4,iv5;
    EditText e1,e2,e3;
   private DatabaseReference myDataBase;
    Button button2,button5,button4;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        iv5 = findViewById(R.id.iv5);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString();
                String contact = e2.getText().toString();
                String countrycode = e3.getText().toString();
                String finNumber = "+" + countrycode + contact;
                if(name.isEmpty() || contact.isEmpty() || countrycode.isEmpty()){
                    Toast.makeText(MainActivity2.this,"All fields required !",Toast.LENGTH_SHORT).show();
                }
                else if(countrycode.length() != 2){
                    Toast.makeText(MainActivity2.this,"Enter the valid country code",Toast.LENGTH_SHORT).show();
                }
                else{
                         myDataBase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                         myDataBase.addValueEventListener(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   for(DataSnapshot data : snapshot.getChildren()){
                                        String real_name = data.child("Username").getValue().toString();
                                        String real_phone = data.child("UserPhoneNumber").getValue().toString();
                                        if(real_phone.equals(finNumber) && real_name.equals(name)){
                                            Toast.makeText(MainActivity2.this,"User Already Exist",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        else if(real_phone.equals(finNumber) ){
                                            Toast.makeText(MainActivity2.this,"Phone number already registered !",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        else{
                                            Toast.makeText(MainActivity2.this,"Moving to next step !",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                                            intent.putExtra(PHONE,countrycode+contact);
                                            startActivity(intent);
                                            break;
                                        }
                                   }

                             }

                             @Override
                             public void onCancelled(@NonNull DatabaseError error) {

                             }
                         });

                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
         startActivity(intent);

            }
        });
     button4.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String name = e1.getText().toString();
             String contact = e2.getText().toString();
             String countrycode = e3.getText().toString();
             String finNumber = "+" + countrycode + contact;
             if (name.isEmpty() || contact.isEmpty() || countrycode.isEmpty()) {
                 Toast.makeText(MainActivity2.this, "All fields required !", Toast.LENGTH_SHORT).show();
             } else if (countrycode.length() != 2) {
                 Toast.makeText(MainActivity2.this, "Enter the valid country code", Toast.LENGTH_SHORT).show();
             } else {
                 Toast.makeText(MainActivity2.this, "Moving to next step !", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                 intent.putExtra(PHONE, countrycode + contact);
                 startActivity(intent);
             }
         }
     });
    }
}