package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity4 extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5;
    ImageView imageView;
    TextView textView;
    public String getunid;
    public static final String UNIQ_ID = "hiiii";
    public static final String GIV_UID = "my_uid";
    public static final int REQUEST_IMAGE_GET = 1;
    private DatabaseReference mDatabase;
    private DatabaseReference zDatabase;
    public String created_url="";

    public Uri fullPhotoUri;

    Button button6, button7, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button3 = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        textView = findViewById(R.id.textView);
        Intent mintent = getIntent();
        String getPhno = mintent.getStringExtra(MainActivity3.PHONE_NUMBER);
        getunid = mintent.getStringExtra(MainActivity3.UN_ID);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nusername = ed1.getText().toString();
                String nuserid = ed2.getText().toString();
                zDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                zDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dd : snapshot.getChildren()){
                            String user_checkid = dd.child("Userid").getValue().toString();
                            if(user_checkid.equals(nuserid)){
                                nuserid.equals("");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                String nusergender = ed3.getText().toString();
                String nuserage = ed4.getText().toString();
                String nuserpassword = ed5.getText().toString();
                if (nusername.isEmpty() || nuserage.isEmpty() || nuserid.isEmpty() || nusergender.isEmpty() || nuserpassword.isEmpty()) {
                    Toast.makeText(MainActivity4.this, "Enter all the required fields", Toast.LENGTH_SHORT).show();
                } else if (nuserage.length() < 2 || nuserage.length() > 2) {
                    Toast.makeText(MainActivity4.this, "Enter a age between 16 to 80 !", Toast.LENGTH_SHORT).show();
                } else if (!nusergender.equalsIgnoreCase("Male") && !nusergender.equalsIgnoreCase("Female") && !nusergender.equalsIgnoreCase("Others")) {
                    Toast.makeText(MainActivity4.this, "Enter among Male , Female and Others !", Toast.LENGTH_SHORT).show();
                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                    mDatabase.child(getunid).child("Userid").setValue(nuserid);
                    mDatabase.child(getunid).child("UserPhoneNumber").setValue(getPhno);
                    mDatabase.child(getunid).child("Username").setValue(nusername);
                    mDatabase.child(getunid).child("Usergender").setValue(nusergender);
                    mDatabase.child(getunid).child("Userage").setValue(nuserage);
                    mDatabase.child(getunid).child("Userpassword").setValue(nuserpassword);
                    mDatabase.child(getunid).child("Userimageurl").setValue(created_url);
                    mDatabase.child(getunid).child("Userq1").setValue("");
                    mDatabase.child(getunid).child("Userq2").setValue("");
                    mDatabase.child(getunid).child("Userq3").setValue("");
                    mDatabase.child(getunid).child("Userq4").setValue("");
                    mDatabase.child(getunid).child("Userq5").setValue("");
                    Intent seeintent = new Intent(MainActivity4.this, MainActivity7.class);
                    seeintent.putExtra(GIV_UID,getunid);
                    startActivity(seeintent);
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fullPhotoUri != null) {
                    uploadToFirebase(fullPhotoUri);
                } else {
                    Toast.makeText(MainActivity4.this, "No image url !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            imageView.setImageURI(data.getData());
            fullPhotoUri = data.getData();
            Toast.makeText(MainActivity4.this, "Uploading image :- " + fullPhotoUri.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void uploadToFirebase(Uri muri) {
        StorageReference mStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://lovebug-65b95.appspot.com").child(getunid).child(getunid + ".profile");
           mStorage.putFile(muri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                             created_url = uri.toString();
                             Toast.makeText(MainActivity4.this,"Saving Successfylly !",Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(MainActivity4.this, "Uploading Failed ! ", Toast.LENGTH_SHORT).show();
               }
           });
    }

}