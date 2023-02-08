package com.example.lovebug;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.TimeUnit;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    ImageView iv6;
    TextView tv2, tv3, textView4;
    EditText e4;
    FirebaseAuth mauth;
    Button btt3, bttt2, bttt1;
    String verifyId;
    DatabaseReference qdatabase;
    StorageReference myStorage;
    public String phno;
    public static final String UN_ID = "sds";
    public static final String PHONE_NUMBER = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bttt2 = findViewById(R.id.be2);
        bttt1 = findViewById(R.id.be1);
        btt3 = findViewById(R.id.btt3);
        textView4 = findViewById(R.id.textView4);
        iv6 = findViewById(R.id.it1);
        e4 = findViewById(R.id.ec1);
        tv2 = findViewById(R.id.tc2);
        tv3 = findViewById(R.id.tt1);
        //in order to send the verification code to the user
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra(MainActivity2.PHONE);
        String finNum = "+" + phoneNumber;
        phno = finNum;
        tv2.setText(finNum);
        mauth = FirebaseAuth.getInstance();
        bttt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtp(finNum);
            }
        });
        bttt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e4.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity3.this, "Enter the otp first", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCode(e4.getText().toString());
                }
            }
        });
        btt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e4.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity3.this, "Enter OTP before to delete the user !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Authenticating !", Toast.LENGTH_SHORT).show();
                    verifyDelete(e4.getText().toString());
                    Toast.makeText(MainActivity3.this, "Deleting the user!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void sendOtp(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mauth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(MainActivity3.this, "Verification Failed !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(verificationId, token);
            verifyId = verificationId;

        }
    };

    public void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyId, code);
        signInByCredential(credential);
    }

    public void verifyDelete(String code) {
        PhoneAuthCredential cred = PhoneAuthProvider.getCredential(verifyId, code);
        signInByCred(cred);
    }

    private void signInByCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = task.getResult().getUser();
                    String my_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    Intent nintent = new Intent(MainActivity3.this, MainActivity4.class);
                    nintent.putExtra(PHONE_NUMBER, phno);
                    nintent.putExtra(UN_ID, my_uid);
                    startActivity(nintent);
                    Toast.makeText(MainActivity3.this, "Moving to next step !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInByCred(PhoneAuthCredential cred) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(cred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser users = task.getResult().getUser();
                    String my_uid_l = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    myStorage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://lovebug-65b95.appspot.com").child(my_uid_l);
                    myStorage.child(my_uid_l+".profile").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity3.this, "Deleting the Storage !", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity3.this, "Sorry , Unable to delete the storage !", Toast.LENGTH_SHORT).show();
                        }
                    });
                    qdatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/");
                    qdatabase.child(my_uid_l).removeValue();

                    FirebaseUser my_user = firebaseAuth.getCurrentUser();
                    if (my_user != null) {
                        my_user.reauthenticate(cred).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                my_user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            textView4.setText("User account deleted !");
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });

    }
}