package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class bookAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    String SelectedPath;
    String selectedDoctor;
    TextView title1b, title2b, title3b, title4b,title5b;
    String username;
    ImageView image1b;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    AppointmentHelperClass helperClass;
    String str,datetime;
    String imgurl,speciality;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();

        title1b = findViewById(R.id.title1b);
        title2b = findViewById(R.id.title2b);
        title3b = findViewById(R.id.title3b);
        title4b = findViewById(R.id.title4b);
        title5b = findViewById(R.id.title5b);
        image1b = findViewById(R.id.image1b);
        Bundle extras = getIntent().getExtras();
        SelectedPath = extras.getString("speciality");
        selectedDoctor = extras.getString("selectedDoctor");
        title5b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(bookAppointment.this, bookAppointment.this,year, month,day);
                datePickerDialog.show();
            }
        });

        title4b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                databaseReference = rootNode.getReference("appointments/" + username);
                helperClass = new AppointmentHelperClass(username,selectedDoctor,datetime,imgurl,speciality);
                databaseReference.child(selectedDoctor).setValue(helperClass);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference = FirebaseDatabase.getInstance().getReference(SelectedPath);
        Query userdetails2 = databaseReference.orderByChild(selectedDoctor);
        userdetails2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String doctorFromDb = dataSnapshot.child(selectedDoctor).child("doctorname").getValue(String.class);
                    String experienceFromDb = dataSnapshot.child(selectedDoctor).child("selectedExperience").getValue(String.class);
                    String specialityFromDb = dataSnapshot.child(selectedDoctor).child("selectedSpeciality").getValue(String.class);
                    String imgurlFromDb = dataSnapshot.child(selectedDoctor).child("imgurl").getValue(String.class);
                    title1b.setText(doctorFromDb);
                    title2b.setText(specialityFromDb);
                    title3b.setText(experienceFromDb);
                    Picasso.get().load(imgurlFromDb).into(image1b);
                    imgurl=imgurlFromDb;
                    speciality=specialityFromDb;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(bookAppointment.this, bookAppointment.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MMMM");
        Date date1 = new Date(myYear, myMonth, myday-1);
        String dayOfWeek = simpledateformat.format(date1);
        boolean isPM = (hourOfDay >= 12);
        String time=String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM");
        String date=dayOfWeek;
        datetime=myday+","+date+","+time;
    }
}