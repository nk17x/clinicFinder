package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class cold extends AppCompatActivity {
    private RecyclerView recyclercold;
    private ColdPostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold);
        recyclercold=findViewById(R.id.recyclercold);
        recyclercold.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<gastricpost> options =
                new FirebaseRecyclerOptions.Builder<gastricpost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors/General Physician"), gastricpost.class)
                        .build();

        adapter=new ColdPostAdapter(options);
        recyclercold.setAdapter(adapter);
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