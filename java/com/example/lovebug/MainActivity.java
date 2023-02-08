package com.example.lovebug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageView iv,iv2;
    TextView tv;
    Button button;
    private FirebaseAuth meeAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        iv = findViewById(R.id.iv);
        iv2 = findViewById(R.id.iv2);
        tv = findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meeAuth = FirebaseAuth.getInstance();
                FirebaseUser myuser = meeAuth.getCurrentUser();
                if(myuser != null){
                    Intent inti = new Intent(MainActivity.this,MainActivity8.class);
                    startActivity(inti);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }

            }
        });
    }
}