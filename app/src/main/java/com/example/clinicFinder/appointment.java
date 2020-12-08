package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class appointment extends AppCompatActivity {
    FirebaseAuth mAuth;
    private RecyclerView recyclerappointment;
    private AppointmentPostAdapter adapter;
    String str;
    String username;
    TextView textView46;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        mAuth=FirebaseAuth.getInstance();
        textView46=findViewById(R.id.textView46);
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
        textView46.setVisibility(View.INVISIBLE);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(recyclerappointment.getChildCount()==0){
                    textView46.setVisibility(View.VISIBLE);
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
                if(recyclerappointment.getChildCount()==0){
                    textView46.setVisibility(View.VISIBLE);
                } else{
                    textView46.setVisibility(View.GONE);
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