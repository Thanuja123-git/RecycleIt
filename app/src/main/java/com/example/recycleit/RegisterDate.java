package com.example.recycleit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterDate extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();

    // creating variables for our edit text
    private EditText companynameEdt, dateofcollEdt, codeEdt;

    // creating variable for button
    private Button submitBtn, viewCoursesBtn;

    // creating a strings for storing
    // our values from edittext fields.
    private String companyname, dateofcoll, code;

    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_date);
        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        // initializing our edittext and buttons
        companynameEdt = findViewById(R.id.edtcompanyname);
        codeEdt = findViewById(R.id.edtcode);
        dateofcollEdt = findViewById(R.id.edtdateofcoll);
        submitBtn = findViewById(R.id.idBtnSubmit);
        viewCoursesBtn = findViewById(R.id.idBtnViewCourses);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        dateofcollEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterDate.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // adding onclick listener to view data in new activity

        // adding on click listener for button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                companyname = companynameEdt.getText().toString();
                code = codeEdt.getText().toString();
                dateofcoll = dateofcollEdt.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(companyname)) {
                    companynameEdt.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(code)) {
                    codeEdt.setError("Please enter Course Description");
                } else if (TextUtils.isEmpty(dateofcoll)) {
                    dateofcollEdt.setError("Please enter Course Duration");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(companyname, code, dateofcoll);
                   
                }

            }
        });
    }

    private void addDataToFirestore(String courseName, String courseDescription, String courseDuration) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbCourses = db.collection("Date of Collection");

        // adding our data to our courses object class.
        Courses courses = new Courses(courseName, courseDescription, courseDuration);

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(courses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(RegisterDate.this, "Your Details has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(RegisterDate.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        dateofcollEdt.setText(dateFormat.format(myCalendar.getTime()));
    }

}