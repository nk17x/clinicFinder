package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class lab_reports extends AppCompatActivity {
   Button button15;
    FirebaseAuth mAuth;
    private RecyclerView recyclerreports;
    private ReportsPostAdapter adapter;
    String str;
    String username;
    TextView textView47;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_reports);
        button15=findViewById(R.id.button15);
        textView47=findViewById(R.id.textView47);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(lab_reports.this,medicalreports.class);
                startActivity(intent);
                finish();
            }
        });
        mAuth= FirebaseAuth.getInstance();

        str= mAuth.getCurrentUser().getEmail();
        username="";
        if(str.indexOf(".")!=-1){
            String [] twoStringArray2= str.split("@",2);
            String username2= twoStringArray2[0];
            String [] twoStringArray= username2.split("\\.",2);
            username= twoStringArray[0];
        }else{
            String [] twoStringArray2= str.split("@",2);
            username= twoStringArray2[0];}
        recyclerreports=findViewById(R.id.recyclerreports);
        recyclerreports.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<reportspost> options =
                new FirebaseRecyclerOptions.Builder<reportspost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("reports/"+username), reportspost.class)
                        .build();
        adapter=new ReportsPostAdapter(options);
        recyclerreports.setAdapter(adapter);
        textView47.setVisibility(View.INVISIBLE);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(recyclerreports.getChildCount()==0){
                    textView47.setVisibility(View.VISIBLE);
                }
            }
        }, 2100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(recyclerreports.getChildCount()==0){
                    textView47.setVisibility(View.VISIBLE);
                } else{
                    textView47.setVisibility(View.GONE);
                }
            }
        }, 2100);

    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}