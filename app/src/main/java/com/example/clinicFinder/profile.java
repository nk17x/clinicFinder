package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    TextInputEditText prophoneno,profullname,proemail;
    Button proupdate,proback;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String protemail,protphone,protfullname;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth=FirebaseAuth.getInstance();
        proupdate=findViewById(R.id.proupdate);
        proback=findViewById(R.id.proback);
        prophoneno=findViewById(R.id.prophoneno);
        proemail=findViewById(R.id.proemail);
        profullname=findViewById(R.id.profullname);
        String email=mAuth.getCurrentUser().getEmail();
        String str = email;
        if(str.indexOf(".")!=-1){
            String [] twoStringArray2= str.split("@",2);
            String username2= twoStringArray2[0];
            String [] twoStringArray= username2.split("\\.",2);
            username= twoStringArray[0];
        }else{
            String [] twoStringArray2= str.split("@",2);
            username= twoStringArray2[0];}
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("users");
        Query userdetails2 = databaseReference.orderByChild("username").equalTo(username);
        userdetails2.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String fullnameFromDb=dataSnapshot.child(username).child("fullname").getValue(String.class);
                    String phoneFromDb=dataSnapshot.child(username).child("phone").getValue(String.class);
                    String emailFromDb=dataSnapshot.child(username).child("email").getValue(String.class);
                    Toast.makeText(profile.this, emailFromDb, Toast.LENGTH_SHORT).show();
                    proemail.setText(emailFromDb);
                    profullname.setText(fullnameFromDb);
                    prophoneno.setText(phoneFromDb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }});

        proback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(profile.this,login.class);
                startActivity(i);
                finish();
            }
        });

        proupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                protphone=prophoneno.getText().toString();
                protfullname =profullname.getText().toString();
                protemail=proemail.getText().toString();
                String str = protemail;
                String username=null;
                if(str.indexOf(".")!=-1){
                    String [] twoStringArray2= str.split("@",2);
                    String username2= twoStringArray2[0];
                    String [] twoStringArray= username2.split("\\.",2);
                    username= twoStringArray[0];
                }else{
                    String [] twoStringArray2= str.split("@",2);
                    username= twoStringArray2[0];}
                UserHelperClass helperClass= new UserHelperClass(protfullname,protphone,protemail,username);
                databaseReference.child(username).setValue(helperClass);
                if(TextUtils.isEmpty(protemail)){
                   proemail.setError("enter email");
                    return;
                }
                if(TextUtils.isEmpty(protphone)){
                    prophoneno.setError("enter phone no");
                    return;
                }
                if(TextUtils.isEmpty(protfullname)){
                    profullname.setError("enter fullname");
                    return;}
            }
        });
    }
}