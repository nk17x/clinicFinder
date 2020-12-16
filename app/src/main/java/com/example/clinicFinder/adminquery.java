package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class adminquery extends AppCompatActivity {
    private RecyclerView recycleradminquery;
    private AdminQueryPostAdapter adapter;
    FirebaseAuth mAuth;
    String str;
    String usernameq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminquery);
        mAuth= FirebaseAuth.getInstance();
        String email=mAuth.getCurrentUser().getEmail();
        str = email;
        if(str.indexOf(".")!=-1){
            String [] twoStringArray2= str.split("@",2);
            String username2= twoStringArray2[0];
            String [] twoStringArray= username2.split("\\.",2);
            usernameq= twoStringArray[0];
        }else{
            String [] twoStringArray2= str.split("@",2);
            usernameq= twoStringArray2[0];}
        recycleradminquery=findViewById(R.id.recycleradminquery);
        recycleradminquery.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<querypost> options =
                new FirebaseRecyclerOptions.Builder<querypost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("querys/khannadeema"), querypost.class)
                        .build();

        adapter=new AdminQueryPostAdapter(options);
        recycleradminquery.setAdapter(adapter);
       
        

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}