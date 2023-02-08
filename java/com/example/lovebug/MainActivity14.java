package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity14 extends AppCompatActivity {
    RecyclerView recyclerView;
         FirebaseAuth mauth;
         TextView textha;
         private DatabaseReference mDatabase;
         private  DatabaseReference ccd;
    ArrayList<String> one;
    ArrayList<String> two;

    public String user_id;
    public String user_gen;
    public String user_q1;
    public String user_q2;
    public String user_q3;
    public String user_q4;
    public String user_q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        textha = findViewById(R.id.textha);
        two = new ArrayList<>();
        one = new ArrayList<>();

        mauth = FirebaseAuth.getInstance();
        String unique = mauth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity14.this));
        CustomAdapter ad = new CustomAdapter(one,two);

         mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
         mDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 user_id = snapshot.child(unique).child("Userid").getValue().toString();
                 user_gen = snapshot.child(unique).child("Usergender").getValue().toString();
                 user_q1 = snapshot.child(unique).child("Userq1").getValue().toString();
                 user_q2 = snapshot.child(unique).child("Userq2").getValue().toString();
                 user_q3 = snapshot.child(unique).child("Userq3").getValue().toString();
                 user_q4 = snapshot.child(unique).child("Userq4").getValue().toString();
                 user_q5 = snapshot.child(unique).child("Userq5").getValue().toString();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
         ccd = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
         ccd.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 int count = 0;
                 for(DataSnapshot sn : snapshot.getChildren()){

                    String take_id = sn.child("Userid").getValue().toString();
                     String take_gen = sn.child("Usergender").getValue().toString();
                     String take_name = sn.child("Username").getValue().toString();
                     String take_age = sn.child("Userage").getValue().toString();
                     String take_1 = sn.child("Userq1").getValue().toString();
                     String take_2 = sn.child("Userq2").getValue().toString();
                     String take_3 = sn.child("Userq3").getValue().toString();
                     String take_4 = sn.child("Userq4").getValue().toString();
                     String take_5 = sn.child("Userq5").getValue().toString();
                     String take_pic = sn.child("Userimageurl").getValue().toString();


                          if(!user_id.equals(take_id)){
                              if((user_gen.equalsIgnoreCase("Male") && take_gen.equalsIgnoreCase("Female")) && (user_q5.equals("TAKEN") && take_5.equals("TAKEN")) && (user_q3.equals("FRIENDSHIP") && take_3.equals("FRIENDSHIP")) ){
                                   one.add(take_id);
                                   two.add(take_name + "   " + take_age);

                                   count++;
                              }
                              else if((user_gen.equalsIgnoreCase("Male") && take_gen.equalsIgnoreCase("Female")) && (user_q5.equals("SINGLE") && take_5.equals("SINGLE")) && (user_q3.equals("DATING") && take_3.equals("DATING"))){
                                  one.add(take_id);
                                  two.add(take_name + "   " + take_age);

                                  count++;
                              }
                              else if((user_gen.equalsIgnoreCase("Female") && take_gen.equalsIgnoreCase("Male")) && (user_q5.equals("TAKEN") && take_5.equals("TAKEN")) && (user_q3.equals("FRIENDSHIP") && take_3.equals("FRIENDSHIP"))){
                                  one.add(take_id);
                                  two.add(take_name + "   " + take_age);
                                  count++;
                              }
                              else if((user_gen.equalsIgnoreCase("Female") && take_gen.equalsIgnoreCase("Male")) && (user_q5.equals("SINGLE") && take_5.equals("SINGLE")) && (user_q3.equals("DATING") && take_3.equals("DATING"))){
                                  one.add(take_id);
                                  two.add(take_name + "   " + take_age);
                                  count++;
                              }
                              else if((user_gen.equalsIgnoreCase("Male") && take_gen.equalsIgnoreCase("Female")) && (user_q1.equals(take_1) || user_q2.equals(take_2) || user_q3.equals(take_3))){
                                  one.add(take_id);
                                  two.add(take_name + "   " + take_age);
                                  count++;
                              }
                              else if((user_gen.equalsIgnoreCase("Female") && take_gen.equalsIgnoreCase("Male")) && (user_q1.equals(take_1) || user_q2.equals(take_2) || user_q3.equals(take_3))){
                                  one.add(take_id);
                                  two.add(take_name + "   " + take_age);

                                  count++;
                              }
                              else if((user_gen.equalsIgnoreCase("Others") && take_gen.equalsIgnoreCase("Others"))&&(user_q1.equals(take_1) || user_q2.equals(take_2) || user_q3.equals(take_3))){
                                  one.add(take_id);
                                  two.add(take_name + "   " + take_age);
                                  count++;
                              }


                          }
                 }
                 if(count != 0){
                    textha.setText("            Congratulations!");
                 }
                 else{
                     textha.setText("Sorry, unable to find any match !");
                 }

                 recyclerView.setAdapter(ad);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
             recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                 @Override
                 public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                     Toast.makeText(MainActivity14.this,"Opening !", Toast.LENGTH_SHORT).show();
                     Intent iss = new Intent(MainActivity14.this,MainActivity18.class);
                     startActivity(iss);
                     return false;
                 }

                 @Override
                 public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                 }

                 @Override
                 public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                 }

             });
    }

}