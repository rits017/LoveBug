package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class MainActivity17 extends AppCompatActivity {
           RecyclerView rv17;
           ListView lv17;
           ImageView i171;
           TextView t171;
           ArrayList<String> notifyc;
           private FirebaseAuth zeeAuth;
           private DatabaseReference cod;
           public String uniq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        rv17 = findViewById(R.id.rv17);
        rv17.setLayoutManager(new LinearLayoutManager(MainActivity17.this));
        i171 = findViewById(R.id.i171);
        t171 = findViewById(R.id.t171);
        zeeAuth = FirebaseAuth.getInstance();
        uniq = zeeAuth.getCurrentUser().getUid();
        notifyc = new ArrayList<>();
        CustomAdapter3 adap = new CustomAdapter3(notifyc);
        cod = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/").child(uniq).child("Message");
        cod.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int ans = 0;
                for(DataSnapshot dp : snapshot.getChildren()){
                    String notification = dp.getKey();
                    String value = dp.getValue().toString();

                    if(!value.equals("")){
                        notifyc.add(" # You received a message from ~ " + notification + ":- " + value);
                        rv17.setAdapter(adap);
                        ans++;
                    }
                }

                if(ans ==0){
                    Toast.makeText(MainActivity17.this, "You don't have any notifications !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}