package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity13 extends AppCompatActivity {
 Button b131;
 TextView t131;
 ImageView i131;
 public Uri fullPhotoUri_2;
 public String uuuid;
 public String created_url_2;
 private DatabaseReference see;
 public static final int REQUEST_IMAGE_GET = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        i131 = findViewById(R.id.i131);
        t131 = findViewById(R.id.t131);
        b131 = findViewById(R.id.b131);
        Intent taking = getIntent();
         uuuid = taking.getStringExtra(MainActivity9.DEKH_UID_PR);
        i131.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);

                }
            }
        });
        b131.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullPhotoUri_2 != null) {
                    uploadToFirebase(fullPhotoUri_2);
                } else {
                    Toast.makeText(MainActivity13.this, "No image url !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            i131.setImageURI(data.getData());
            fullPhotoUri_2 = data.getData();
            Toast.makeText(MainActivity13.this, "Uploading image :- " + fullPhotoUri_2.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void uploadToFirebase(Uri kuri){
        StorageReference mStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://lovebug-65b95.appspot.com").child(uuuid).child(uuuid + ".profile");
        mStorage.putFile(kuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        created_url_2 = uri.toString();
                         see = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                         see.child(uuuid).child("Userimageurl").setValue(created_url_2);
                        Toast.makeText(MainActivity13.this,"Saving Successfylly !",Toast.LENGTH_SHORT).show();
                        Intent abi = new Intent(MainActivity13.this,MainActivity9.class);
                        startActivity(abi);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity13.this, "Uploading Failed ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}