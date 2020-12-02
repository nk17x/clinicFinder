package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class search_doctors extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private RecyclerView recyclersearch;
    SearchPostAdapter adapter;
    Spinner dynamicListSpinner;
    String selectedSpeciality;
    Button button11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctors);
        button11=findViewById(R.id.button11);
        dynamicListSpinner = findViewById(R.id.spinnersearch);
        List<CharSequence> choices = new ArrayList<>();
        choices.add("General Physician");
        choices.add("Gynaecologist");
        choices.add("Dermatologist");
        choices.add("Neurologist");
        choices.add("Gastrology");
        choices.add("pediatrics");
        choices.add("urology");
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dynamicListSpinner.setAdapter(adapter3);
        dynamicListSpinner.setOnItemSelectedListener(this);
        recyclersearch=findViewById(R.id.recyclersearch);
        recyclersearch.setLayoutManager(new LinearLayoutManager(this));

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<gastricpost> options =
                        new FirebaseRecyclerOptions.Builder<gastricpost>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors/"+selectedSpeciality), gastricpost.class)
                                .build();

                adapter=new SearchPostAdapter(options);
                recyclersearch.setAdapter(adapter);
                adapter.startListening();
            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedSpeciality = dynamicListSpinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}