package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class addoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner dynamicListSpinner,experiencespinner;
    EditText editTextDoctorName;
    TextView textView44;
    Button button10;
    String selectedExperience;
    String doctorname;
    String selectedSpeciality;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    DoctorHelperClass helperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoctor);
        mAuth=FirebaseAuth.getInstance();
        textView44=findViewById(R.id.textView44);
        editTextDoctorName=findViewById(R.id.editTextdoctorname);
        button10=findViewById(R.id.button10);
        dynamicListSpinner = findViewById(R.id.spinner1);
        experiencespinner = findViewById(R.id.spinner2);
        List<CharSequence> choices = new ArrayList<>();
        choices.add("General Physician");
        choices.add("Gynaecologist");
        choices.add("Dermatologist");
        choices.add("Neurologist");
        choices.add("Gasrtology");
        choices.add("pediatrics");
        choices.add("urology");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dynamicListSpinner.setAdapter(adapter);
        dynamicListSpinner.setOnItemSelectedListener(this);
        List<CharSequence> choices2 = new ArrayList<>();
        choices2.add("1 years");
        choices2.add("2 years");
        choices2.add("3 years");
        choices2.add("4 years");
        choices2.add("5 years");
        choices2.add("6 years");
        choices2.add("7 years");
        choices2.add("8 years");
        choices2.add("9 years");
        choices2.add("10 years");
        choices2.add("11 years");
        choices2.add("12 years");
        choices2.add("13 years");
        choices2.add("14 years");
        choices2.add("15 years");
        choices2.add("16 years");
        choices2.add("17 years");
        choices2.add("18 years");
        choices2.add("19 years");
        choices2.add("20 years");
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        experiencespinner.setAdapter(adapter2);
        experiencespinner.setOnItemSelectedListener(this);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctorname=editTextDoctorName.getText().toString();
                Toast.makeText(addoctor.this, doctorname+" "+selectedSpeciality+" "+selectedExperience, Toast.LENGTH_SHORT).show();
                rootNode=FirebaseDatabase.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("doctors");
                helperClass= new DoctorHelperClass(doctorname,selectedSpeciality,selectedExperience);
                databaseReference.child(selectedSpeciality).child(doctorname).setValue(helperClass);
            }
        });

    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedSpeciality = dynamicListSpinner.getSelectedItem().toString();
        selectedExperience = experiencespinner.getSelectedItem().toString();


    }
    public void onNothingSelected(AdapterView<?> parent) {
    }


}
