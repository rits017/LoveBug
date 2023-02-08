package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity18 extends AppCompatActivity {
        EditText ed181;
        Button b181;
        private DatabaseReference zeebase;
        private FirebaseAuth zeeauth;
        public String uni;
        public static final String MYCHILD = "me";
        public static final String HECHILD = "he";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
        ed181 = findViewById(R.id.ed181);
        b181 = findViewById(R.id.b181);
         zeeauth = FirebaseAuth.getInstance();
         uni = zeeauth.getCurrentUser().getUid();
         b181.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 String myid = ed181.getText().toString();
                 if(myid.isEmpty()){
                     Toast.makeText(MainActivity18.this, "Enter user id first !", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     zeebase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                     zeebase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             int a = 0;
                             for(DataSnapshot dd : snapshot.getChildren()){
                                 String seek_id = dd.child("Userid").getValue().toString();
                                 if(seek_id.equals(myid)){
                                     String main_child = dd.getKey();
                                     Intent iff = new Intent(MainActivity18.this,MainActivity19.class);
                                      iff.putExtra(MYCHILD,uni);
                                      iff.putExtra(HECHILD,main_child);
                                      startActivity(iff);
                                      a++;
                                      break;
                                 }
                             }
                             if(a==0){
                                 Toast.makeText(MainActivity18.this, "Enter correct user id !", Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });
                 }
             }
         });
    }
}