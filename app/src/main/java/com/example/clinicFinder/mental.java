package com.example.clinicFinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class mental extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental);
        RecyclerView mentallist=(RecyclerView) findViewById(R.id.mentallist);
        mentallist.setLayoutManager(new LinearLayoutManager(this));

        String[] names={"nadeem","zeeshan","hello","there","nadeem","zeeshan","hello","there","nadeem","zeeshan","hello","there"};
        String[] yearsexp={"5 years","7 years","3 years","4 years","5 years","7 years","3 years","4 years","5 years","7 years","3 years","4 years"};
        mentallist.setAdapter(new mentalAdapter(names,yearsexp));
    }
}