package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
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
    TextView title1b, title2b, title3b;
    Button button13,button14;
    String username;
    ImageView image1b;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    AppointmentHelperClass helperClass;
    String str,dbdate,dbtime;
    String imgurl,speciality;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear;
    boolean dateSelected=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();

        title1b = findViewById(R.id.title1b);
        title2b = findViewById(R.id.title2b);
        title3b = findViewById(R.id.title3b);

        image1b = findViewById(R.id.image1b);
        button13=findViewById(R.id.button13);
        button14=findViewById(R.id.button14);
        Bundle extras = getIntent().getExtras();
        SelectedPath = extras.getString("speciality");
        selectedDoctor = extras.getString("selectedDoctor");
        button13.setOnClickListener(new View.OnClickListener() {
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

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(dateSelected) {
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

                databaseReference = rootNode.getReference("appointments/" + username);
                helperClass = new AppointmentHelperClass(username, selectedDoctor, dbtime,dbdate, imgurl, speciality);
                databaseReference.child(selectedDoctor).setValue(helperClass);
                Toast.makeText(bookAppointment.this, "Appointment Booking Successful", Toast.LENGTH_SHORT).show();
                showNotification("Appointment Booked Successfully", "Tap here to view your Appointments");
            }else{
                Toast.makeText(bookAppointment.this, "Please Select Timing for your Appointment", Toast.LENGTH_SHORT).show();
            }
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
                    title1b.setText("Dr."+doctorFromDb.toUpperCase());
                    title2b.setText(specialityFromDb);
                    title3b.setText(experienceFromDb+" Experience");
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
        String month1=dayOfWeek;
        dbdate=month1+" "+myday;
        dbtime=time;
        dateSelected=true;

    }
    void showNotification(String title, String message) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.drawable.heart_health) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getApplicationContext(), appointment.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}