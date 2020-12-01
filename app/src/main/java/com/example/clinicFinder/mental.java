package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class mental extends AppCompatActivity {
    private RecyclerView recyclermental;
    private MentalPostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental);
        recyclermental=findViewById(R.id.recyclermental);
        recyclermental.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<gastricpost> options =
                new FirebaseRecyclerOptions.Builder<gastricpost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors/Neurologist"), gastricpost.class)
                        .build();

        adapter=new MentalPostAdapter(options);
        recyclermental.setAdapter(adapter);
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