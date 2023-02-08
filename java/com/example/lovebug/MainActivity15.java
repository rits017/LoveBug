package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity15 extends AppCompatActivity {
    TextView t151;
    Button b151,b152;
    EditText ed151;
    private DatabaseReference mDatabase;
    public static final String PHONE = "dsa";
    public static final String USER_NUMBER = "dss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);
        t151 = findViewById(R.id.t151);
        b151 = findViewById(R.id.b151);
        b152 = findViewById(R.id.b152);
        ed151 = findViewById(R.id.ed151);
        b151.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String take_id = ed151.getText().toString();
                if (take_id.equals("")) {
                    Toast.makeText(MainActivity15.this, "Enter the user id first !", Toast.LENGTH_SHORT).show();
                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");

                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int valid = 0;
                            for (DataSnapshot sd : snapshot.getChildren()) {
                                String my_id = sd.child("Userid").getValue().toString();
                                if (take_id.equals(my_id)) {
                                    Toast.makeText(MainActivity15.this, " Sending !", Toast.LENGTH_SHORT).show();
                                    String take_unique = sd.getKey();
                                    String phone_num = sd.child("Userid").getValue().toString();
                                    Intent swint = new Intent(MainActivity15.this, MainActivity16.class);
                                    valid++;
                                    swint.putExtra(PHONE, take_unique);
                                    swint.putExtra(USER_NUMBER, phone_num);
                                    startActivity(swint);
                                    break;
                                }

                            }
                            if (valid == 0) {
                                Toast.makeText(MainActivity15.this, "Invalid user id !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        b152.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent hai = new Intent(MainActivity15.this,MainActivity17.class);
           startActivity(hai);
            }
        });
    }

}