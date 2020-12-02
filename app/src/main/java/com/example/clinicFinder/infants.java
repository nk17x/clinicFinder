package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class infants extends AppCompatActivity {
    private RecyclerView recyclerinfants;
    private InfantsPostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infants);
        recyclerinfants=findViewById(R.id.recyclerinfants);
        recyclerinfants.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<gastricpost> options =
                new FirebaseRecyclerOptions.Builder<gastricpost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors/pediatrics"), gastricpost.class)
                        .build();

        adapter=new InfantsPostAdapter(options,this);
        recyclerinfants.setAdapter(adapter);




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
