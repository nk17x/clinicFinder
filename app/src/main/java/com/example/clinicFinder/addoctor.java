package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class addoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner dynamicListSpinner, experiencespinner;
    EditText editTextDoctorName;
    TextView textView44;
    Button button10, button12;
    ProgressBar progressBar;
    ImageView imageView;
    String selectedExperience;
    String doctorname;
    String selectedSpeciality;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    DoctorHelperClass helperClass;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri mImageURi;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoctor);
        mAuth = FirebaseAuth.getInstance();
        textView44 = findViewById(R.id.textView44);
        editTextDoctorName = findViewById(R.id.editTextdoctorname);
        button10 = findViewById(R.id.button10);
        button12 = findViewById(R.id.button12);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView4);
        dynamicListSpinner = findViewById(R.id.spinner1);
        experiencespinner = findViewById(R.id.spinner2);
        List<CharSequence> choices = new ArrayList<>();
        choices.add("General Physician");
        choices.add("Gynaecologist");
        choices.add("Dermatologist");
        choices.add("Neurologist");
        choices.add("Gastrology");
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
                doctorname = editTextDoctorName.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                storageReference = FirebaseStorage.getInstance().getReference("doctors/" + selectedSpeciality);
                databaseReference = FirebaseDatabase.getInstance().getReference("doctors");
                editTextDoctorName.setText("");
                uploadFile();
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

    }/*oncreateends*/

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageURi != null) {
            final StorageReference fileReference = storageReference.child(doctorname + "." + getFileExtension(mImageURi));
            fileReference.putFile(mImageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imgurl = uri.toString();
                            helperClass = new DoctorHelperClass(doctorname, selectedSpeciality, selectedExperience, imgurl);
                            databaseReference.child(selectedSpeciality).child(doctorname).setValue(helperClass);
                        }
                    });
                    Toast.makeText(addoctor.this, "Doctor added Succesfully", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.docdark);

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(addoctor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(this, "image not selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedSpeciality = dynamicListSpinner.getSelectedItem().toString();
        selectedExperience = experiencespinner.getSelectedItem().toString();
    }//this is for dropdown list

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageURi = data.getData();
            Picasso.get().load(mImageURi).into(imageView);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }


}
