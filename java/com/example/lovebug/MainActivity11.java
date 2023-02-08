package com.example.lovebug;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity11 extends AppCompatActivity {
    TextView t111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        t111= findViewById(R.id.t111);
        Toast.makeText(MainActivity11.this, "Providing Developer Information !", Toast.LENGTH_SHORT).show();
    }
}