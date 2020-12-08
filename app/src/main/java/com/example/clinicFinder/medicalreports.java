package com.example.clinicFinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class medicalreports extends AppCompatActivity {
    Button button16, button17;
    private static final int PICK_IMAGE_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Uri mImageURi;
    FirebaseAuth mAuth;
    String str, username, namereport;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    EditText reportname;
    ReportsHelperClass helperClass;
    ImageView imageView6;
    String pictureFilePath;
    Uri uri;
    Context context;
    int counter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalreports);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        imageView6 = findViewById(R.id.imageView6);
        reportname = findViewById(R.id.reportname);


        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reportname.getText().toString()!=""){
                namereport = reportname.getText().toString();
                mAuth = FirebaseAuth.getInstance();
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
                storageReference = FirebaseStorage.getInstance().getReference("reports/" + username);
                databaseReference = FirebaseDatabase.getInstance().getReference("reports");
                uploadFile();}
                else{
                    Toast.makeText(context, "Please Enter Report Name.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void uploadFile() {
        if (mImageURi != null) {
            final StorageReference fileReference = storageReference.child(namereport + "." + getFileExtension(mImageURi));
            fileReference.putFile(mImageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imgurl = uri.toString();
                            helperClass = new ReportsHelperClass(namereport, imgurl);
                            databaseReference.child(username).child(namereport).setValue(helperClass);
                        }
                    });
                    Toast.makeText(medicalreports.this, "Report added Succesfully", Toast.LENGTH_SHORT).show();
                    imageView6.setImageResource(R.drawable.common_google_signin_btn_text_light_normal_background);

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(medicalreports.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent =new Intent(medicalreports.this,lab_reports.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "image not selected", Toast.LENGTH_SHORT).show();
        }
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, 1);
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageURi = data.getData();
            Picasso.get().load(mImageURi).fit().centerCrop().into(imageView6);
        }
    }


}