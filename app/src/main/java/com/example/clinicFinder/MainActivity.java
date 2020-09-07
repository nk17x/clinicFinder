package com.example.clinicFinder;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
/*Button button3;*/
FirebaseAuth mAuth;
FirebaseDatabase rootNode;
DatabaseReference databaseReference;
String email;
String username;
TextView textView22,textView10,textView12,textView11,textView15,textView17,textView18,textView21;
ImageView imageView14,imageView15,imageView17,imageView16,imageView19,imageView18;
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        /*textView22=findViewById(R.id.textView22);*/

        textView10=findViewById(R.id.textView10);
        textView12=findViewById(R.id.textView12);
        textView11=findViewById(R.id.textView11);
        textView15=findViewById(R.id.textView15);
        textView17=findViewById(R.id.textView17);
        textView18=findViewById(R.id.textView18);
        textView21=findViewById(R.id.textView21);
        imageView14=findViewById(R.id.imageView14);
        imageView15=findViewById(R.id.imageView15);
        imageView17=findViewById(R.id.imageView17);
        imageView16=findViewById(R.id.imageView16);
        imageView19=findViewById(R.id.imageView19);
        imageView18=findViewById(R.id.imageView18);
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        email=mAuth.getCurrentUser().getEmail();
        String str = email;
        String [] twoStringArray= str.split("@",2);
        username= twoStringArray[0];
        rootNode=FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query userdetails = databaseReference.orderByChild("username").equalTo(username);
        userdetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String fullnameFromDb=dataSnapshot.child(username).child("fullname").getValue(String.class);
                    String phoneFromDb=dataSnapshot.child(username).child("phone").getValue(String.class);
/*
                    textView22.setText("Hi ,"+fullnameFromDb.toUpperCase());
*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }});
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,cold.class);
                startActivity(i);            }
        });
        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,pregnancy.class);
                startActivity(i);            }
        });
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,acne.class);
                startActivity(i);            }
        });
        textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,mental.class);
                startActivity(i);            }
        });
        textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,gastric.class);
                startActivity(i);            }
        });
        textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,infants.class);
                startActivity(i);            }
        });
        textView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,urine.class);
                startActivity(i);            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,find_clinic.class);
                startActivity(i);
            }
        });
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,search_doctors.class);
                startActivity(i);            }
        });
        imageView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,self_diagnose.class);
                startActivity(i);            }
        });
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,lab_reports.class);
                startActivity(i);            }
        });
        imageView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,order_medicines.class);
                startActivity(i);            }
        });
        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,expert.class);
                startActivity(i);            }
        });



    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navlogout:
                mAuth.signOut();
                Intent intentlogout=new Intent(MainActivity.this,login.class);
                startActivity(intentlogout);
                finish();
                break;
            case R.id.navhome:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}