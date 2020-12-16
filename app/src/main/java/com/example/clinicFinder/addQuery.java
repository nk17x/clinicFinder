package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class addQuery extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner dynamicListSpinner;
    TextView querytopic,querydescribe;
    Button postquery;
    String qproblem,str,username,qtopic,qdescribe;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase rootNode;
    QueryHelperClass helperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);
        mAuth=FirebaseAuth.getInstance();
        postquery=findViewById(R.id.button19);
        querytopic=findViewById(R.id.question);
        querydescribe=findViewById(R.id.description);
        dynamicListSpinner = findViewById(R.id.spinnersearch2);
        List<CharSequence> choices = new ArrayList<>();
        choices.add("--Problem Related To--");
        choices.add("Doctors");
        choices.add("Appointment");
        choices.add("Reports");
        choices.add("App Related");
        choices.add("Other");
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dynamicListSpinner.setAdapter(adapter3);
        dynamicListSpinner.setOnItemSelectedListener(this);
        postquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter=0;
                qtopic=querytopic.getText().toString();
                qdescribe=querydescribe.getText().toString();
                str = mAuth.getCurrentUser().getEmail();
                username = "";
                if (str.indexOf(".") != -1) {
                    String[] twoStringArray2 = str.split("@", 2);
                    String username2 = twoStringArray2[0];
                    String[] twoStringArray = username2.split("\\.", 2);
                    username = twoStringArray[0];
                } else {
                    String[] twoStringArray2 = str.split("@", 2);
                    username = twoStringArray2[0];
                }
                databaseReference=FirebaseDatabase.getInstance().getReference("querys/"+"khannadeema");
                helperClass = new QueryHelperClass(username,qtopic,qproblem,qdescribe);
                databaseReference.child(String.valueOf(qtopic)).setValue(helperClass);

                Toast.makeText(addQuery.this, "Query Posted Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!dynamicListSpinner.getSelectedItem().toString().equals("--Problem Related To--"))
            qproblem = dynamicListSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}