package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {
    Button button;
    EditText editText,editText2,editText5,editText6;
    TextView textView2;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    boolean registerstatus=true;
    String email,password,phone,fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();
        initilaize();
        rootNode=FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(registration.this,login.class);
                startActivity(i);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference=rootNode.getReference("users");
                phone=editText6.getText().toString();
                fullname =editText5.getText().toString();
                email=editText.getText().toString();
                password =editText2.getText().toString();
                String str = email;
                String [] twoStringArray= str.split("@",2);
                final String username= twoStringArray[0];
                UserHelperClass helperClass= new UserHelperClass(fullname,phone,email,password,username);
                databaseReference.child(username).setValue(helperClass);
                    if(TextUtils.isEmpty(email)){
                        editText.setError("enter email");
                        return;
                    }
                   if(TextUtils.isEmpty(password)){
                        editText2.setError("enter password");
                   return;}
                if(TextUtils.isEmpty(phone)){
                    editText6.setError("enter phone no");
                    return;
                }
                if(TextUtils.isEmpty(fullname)){
                    editText5.setError("enter fullname");
                    return;}
                progressBar.setVisibility(View.VISIBLE);
                   if(registerstatus){
                       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   Log.i("login","register is going");
                                   registerstatus=false;
                                   progressBar.setVisibility(View.GONE);
                                   Intent i =new Intent(registration.this,login.class);
                                   startActivity(i);
                                   Toast.makeText(registration.this, "registeration succesfull", Toast.LENGTH_SHORT).show();
                                   mAuth.signOut();
                                    finish();
                               }
                               else{
                                   Toast.makeText(registration.this, "not succesfull", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
                   }
                   else{
                       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   Log.i("login","login is going");
                                   Intent i =new Intent(registration.this,MainActivity.class);
                                   startActivity(i);
                                   finish();
                               }

                           }
                       }); }
                   }



            });
        }

    public void initilaize(){
            button=findViewById(R.id.button);
            editText=findViewById(R.id.editText);
            editText2=findViewById(R.id.editText2);
            editText5=findViewById(R.id.editText5);
            editText6=findViewById(R.id.editText6);
            textView2=findViewById(R.id.textView2);
            progressBar=findViewById(R.id.progressBar);
            progressBar.setVisibility(View.INVISIBLE);
    }
}