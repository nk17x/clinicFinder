package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class appointment extends AppCompatActivity {
    FirebaseAuth mAuth;
    private RecyclerView recyclerappointment;
    private AppointmentPostAdapter adapter;
    String str;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        mAuth=FirebaseAuth.getInstance();
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
        recyclerappointment=findViewById(R.id.recyclerappointment);
        recyclerappointment.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<appointmentpost> options =
                new FirebaseRecyclerOptions.Builder<appointmentpost>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("appointments/"+username), appointmentpost.class)
                        .build();

        adapter=new AppointmentPostAdapter(options);
        recyclerappointment.setAdapter(adapter);


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