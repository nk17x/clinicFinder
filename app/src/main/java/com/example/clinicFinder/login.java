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

public class login extends AppCompatActivity {
    Button button2;
    EditText editText3,editText4;
    TextView textView3;
    ProgressBar progressBar2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        initilaize();
        if(mAuth.getCurrentUser() !=null){
            Intent i =new Intent(login.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(login.this,registration.class);
                startActivity(i);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;
                email = editText3.getText().toString();
                password = editText4.getText().toString();
                String str = email;
                String [] twoStringArray= str.split("@",2);
                final String username= twoStringArray[0];
                if (TextUtils.isEmpty(email)) {
                    editText3.setError("enter email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    editText4.setError("enter password");
                    return;
                }
                progressBar2.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("login","login is going");
                            Intent i =new Intent(login.this,MainActivity.class);
                            i.putExtra("usernameintent",username);
                            startActivity(i);
                            finish();
                        }else{
                            progressBar2.setVisibility(View.INVISIBLE);
                            Toast.makeText(login.this, "enter valid details...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }



    });
}
    public void initilaize() {
        button2 = findViewById(R.id.button2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        textView3 = findViewById(R.id.textView3);
        textView3.setVisibility(View.VISIBLE);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.INVISIBLE);
    }
}