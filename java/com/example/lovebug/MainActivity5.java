package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity5 extends AppCompatActivity {
   EditText ee1,ee2;
   TextView textView2,textView3;
   ImageView imageView2;
   Button button8,button9;
    String check_me = "";
   public static final String USER_MY_ID = "sdsd";
   public  static final  String TAKE_THIS_USERNAME = "uid";
    public  static final  String TAKE_THIS_USERPASSWORD = "ud";
    private DatabaseReference mDatabase;
    private DatabaseReference nDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        imageView2 = findViewById(R.id.imageView2);
        ee1 = findViewById(R.id.ee1);
        ee2 = findViewById(R.id.ee2);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_id_2 = ee1.getText().toString();

                if(user_id_2.isEmpty()){
                    Toast.makeText(MainActivity5.this,"Enter the user id !",Toast.LENGTH_SHORT).show();
                }
                else {
                    mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot snap : snapshot.getChildren()){
                                String my_user_id_check = snap.child("Userid").getValue().toString();
                                check_me = my_user_id_check;
                                if(my_user_id_check.equals(user_id_2)){
                                    Intent hintent = new Intent(MainActivity5.this, MainActivity6.class);
                                    hintent.putExtra(USER_MY_ID, user_id_2);
                                    startActivity(hintent);
                                    Toast.makeText(MainActivity5.this, "Redirecting for Verification !", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                             if(!check_me.equals(user_id_2)){
                                 Toast.makeText(MainActivity5.this,"Not a Valid user id !",Toast.LENGTH_SHORT).show();
                             }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = ee1.getText().toString();
                String user_password = ee2.getText().toString();
                if(user_id.isEmpty() || user_password.isEmpty()){
                    Toast.makeText(MainActivity5.this,"Enter all the required fields !",Toast.LENGTH_SHORT).show();
                }
                else {
                    mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String curr_id="";
                                   String curr_pass="";
                            for(DataSnapshot data : snapshot.getChildren()){
                                String my_user_id = data.child("Userid").getValue().toString();
                                curr_id = my_user_id;
                               String my_user_password = data.child("Userpassword").getValue().toString();
                               curr_pass = my_user_password;
                                     if(my_user_id.equals(user_id) && my_user_password.equals(user_password)){
                                         String take_this_uid = data.getValue().toString();
                                         Toast.makeText(MainActivity5.this,"Logging in Successfully !",Toast.LENGTH_SHORT).show();
                                         Intent pintent = new Intent(MainActivity5.this,MainActivity6.class);
                                         pintent.putExtra(USER_MY_ID,my_user_id);
                                         pintent.putExtra(TAKE_THIS_USERPASSWORD,my_user_password);
                                         startActivity(pintent);
                                         break;
                                     }
                                  else if(!(my_user_id.equals(user_id) && my_user_password.equals(user_password))){
                                         Toast.makeText(MainActivity5.this,"Checking Login Credentials !",Toast.LENGTH_SHORT).show();

                                     }

                            }
                                   if(!curr_id.equals(user_id) || !curr_pass.equals(user_password)){
                                       Toast.makeText(MainActivity5.this,"Invalid Login Credentials !",Toast.LENGTH_SHORT).show();

                                   }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity5.this,"Error Fetching Details !", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}