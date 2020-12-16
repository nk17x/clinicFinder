package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class query extends AppCompatActivity {
Button addquery;
    private RecyclerView recyclerquery;
    private QueryPostAdapter adapter;
    FirebaseAuth mAuth;
    String str;
    String usernameq;
    TextView textView48;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        mAuth= FirebaseAuth.getInstance();
        textView48=findViewById(R.id.textView48);
        addquery=findViewById(R.id.button18);
        addquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(query.this,addQuery.class);
                startActivity(intent);
            }
        });
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
        recyclerquery=findViewById(R.id.recyclerquery);
        recyclerquery.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<querypost> options =
                new FirebaseRecyclerOptions.Builder<querypost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("querys/"+"khannadeema"), querypost.class)
                        .build();

        adapter=new QueryPostAdapter(options);
        recyclerquery.setAdapter(adapter);
        textView48.setVisibility(View.INVISIBLE);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(recyclerquery.getChildCount()==0){
                    textView48.setVisibility(View.VISIBLE);
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
                if(recyclerquery.getChildCount()==0){
                    textView48.setVisibility(View.VISIBLE);
                } else{
                    textView48.setVisibility(View.GONE);
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