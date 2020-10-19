package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;

public class acne extends AppCompatActivity {
    TextView textView1, textView2, textView3, textView4, textView6, textView7, textView8, textView10, textView11, textView12, textView14, textView15, textView16, textView18, textView19, textView20, textView21, textView23, textView24, textView22, textView26, textView28, textView29, textView31, textView32, textView33, textView35, textView36, textView37, textView39, textView40, textView41, textView43, textView44, textView45, textView47, textView48, textView49, textView51, textView52, textView53;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acne);

        textView2 = findViewById(R.id.textViewa2);
        textView3 = findViewById(R.id.textViewa3);
        textView4 = findViewById(R.id.textViewa4);
        textView6 = findViewById(R.id.textViewa6);
        textView7 = findViewById(R.id.textViewa7);
        textView8 = findViewById(R.id.textViewa8);
        textView10 = findViewById(R.id.textViewa10);
        textView11 = findViewById(R.id.textViewa11);
        textView12 = findViewById(R.id.textViewa12);
        textView14 = findViewById(R.id.textViewa14);
        textView15 = findViewById(R.id.textViewa15);
        textView16 = findViewById(R.id.textViewa16);
        textView18 = findViewById(R.id.textViewa18);
        textView19 = findViewById(R.id.textViewa19);
        textView20 = findViewById(R.id.textViewa20);
        textView22 = findViewById(R.id.textViewa22);
        textView23 = findViewById(R.id.textViewa23);
        textView24 = findViewById(R.id.textViewa24);
        textView26 = findViewById(R.id.textViewa26);
        textView28 = findViewById(R.id.textViewa28);
        textView29 = findViewById(R.id.textViewa29);
        textView31 = findViewById(R.id.textViewa31);
        textView32 = findViewById(R.id.textViewa32);
        textView33 = findViewById(R.id.textViewa33);
        textView35 = findViewById(R.id.textViewa35);
        textView36 = findViewById(R.id.textViewa36);
        textView37 = findViewById(R.id.textViewa37);
        textView39 = findViewById(R.id.textViewa39);
        textView40 = findViewById(R.id.textViewa40);
        textView41 = findViewById(R.id.textViewa41);
        textView43 = findViewById(R.id.textViewa43);
        textView44 = findViewById(R.id.textViewa44);
        textView45 = findViewById(R.id.textViewa45);
        textView47 = findViewById(R.id.textViewa47);
        textView48 = findViewById(R.id.textViewa48);
        textView49 = findViewById(R.id.textViewa49);
        textView51 = findViewById(R.id.textViewa51);
        textView52 = findViewById(R.id.textViewa52);
        textView53 = findViewById(R.id.textViewa53);

    }
}