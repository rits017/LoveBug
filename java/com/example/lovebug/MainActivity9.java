package com.example.lovebug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity9 extends AppCompatActivity {
Button b91,b92,b93,b94;
TextView t91,t95;
FirebaseAuth zauth;
public String pro_uid;
public static final String DEKH_UID = "dekh";
public static final String DEKH_UID_PR = "dsd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        b91 = findViewById(R.id.b91);
        b92 = findViewById(R.id.b92);
        t91 = findViewById(R.id.t91);
        t95 = findViewById(R.id.t95);
        b93 = findViewById(R.id.b93);
        b94 = findViewById(R.id.b94);
        zauth = FirebaseAuth.getInstance();
        pro_uid = zauth.getCurrentUser().getUid();
        b91.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  t91.setText("In order to delete the user go to second page and press update user ,you can delete user from there after logging out !");
            }
        });
        t95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent humhain = new Intent(MainActivity9.this,MainActivity11.class);
                startActivity(humhain);
            }
        });
        b93.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent ddin = new Intent(MainActivity9.this,MainActivity12.class);
             ddin.putExtra(DEKH_UID,pro_uid);
             startActivity(ddin);
            }
        });
        b92.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tdin = new Intent(MainActivity9.this,MainActivity13.class);
                tdin.putExtra(DEKH_UID_PR,pro_uid);
                startActivity(tdin);
            }
        });
        b94.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zauth.signOut();
                Toast.makeText(MainActivity9.this, "Logging Out !", Toast.LENGTH_SHORT).show();
                Intent ssss = new Intent(MainActivity9.this,MainActivity.class);
                startActivity(ssss);
            }
        });
    }
}