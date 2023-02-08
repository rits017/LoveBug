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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity6 extends AppCompatActivity {
    ImageView it1;
    TextView tt1;
    EditText ec1,ec2,ec3;
    FirebaseAuth mauth;
    Button be1,be2;
    public String user_taken_id;
    public String user_taken_id2;
    public String user_taken_pass2;
    String verifyId;
    private DatabaseReference qDatabase;
    public static final String GIVE_UID_TO = "dd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        be2= findViewById(R.id.be2);
        be1 = findViewById(R.id.be1);
        it1 = findViewById(R.id.it1);
        ec1 = findViewById(R.id.ec1);
        ec2 = findViewById(R.id.ec2);
        tt1 = findViewById(R.id.tt1);
        ec3 = findViewById(R.id.ec3);
        mauth = FirebaseAuth.getInstance();
        Intent pintent = getIntent();
         user_taken_id = pintent.getStringExtra(MainActivity5.USER_MY_ID);
         user_taken_id2 = pintent.getStringExtra(MainActivity5.TAKE_THIS_USERPASSWORD);
         user_taken_pass2= pintent.getStringExtra(MainActivity5.TAKE_THIS_USERPASSWORD);

        be1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph_number = ec2.getText().toString();
                String cr_code = ec3.getText().toString();
                String fin_number = "+"+ cr_code +ph_number;
                sendOtp(fin_number);

            }
        });
        be2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ec1.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity6.this, "Enter the otp first", Toast.LENGTH_SHORT).show();
                }
                else {
                    verifyCode(ec1.getText().toString());

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
            if(code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(MainActivity6.this,"Verification Failed !",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(verificationId,token);
            verifyId = verificationId;

        }
    };
    public void verifyCode(String code){

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyId,code);
        signInByCredential(credential);
    }
    private void signInByCredential(PhoneAuthCredential credential){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    String my_un_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    qDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lovebug-65b95-default-rtdb.firebaseio.com/").child(my_un_id);
                    qDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                              String value_user_id = snapshot.child("Userid").getValue().toString();
                              if(value_user_id.equals(user_taken_id)){
                                  Toast.makeText(MainActivity6.this,"Logging in !",Toast.LENGTH_SHORT).show();
                                  Intent yintent = new Intent(MainActivity6.this,MainActivity8.class);
                                  yintent.putExtra(GIVE_UID_TO,my_un_id);
                                  startActivity(yintent);

                              }
                              else{
                                  Toast.makeText(MainActivity6.this,"Not a Valid User !",Toast.LENGTH_SHORT).show();
                              }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity6.this, "Error Fetching Details!", Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }
        });
    }
}