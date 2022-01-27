package com.example.recycleit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class CompanyPage extends AppCompatActivity {

    private Button submitCourseBtn, viewCoursesBtn;

    // creating a strings for storing
    // our values from edittext fields.

    // creating a variable
    // for firebasefirestore.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_page);

        // getting our instance
        // from Firebase Firestore.

        viewCoursesBtn = findViewById(R.id.idBtnViewCourses);
        submitCourseBtn = findViewById(R.id.idBtnSubmitCourse);
        // adding onclick listener to view data in new activity
        viewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity on button click
                Intent i = new Intent(CompanyPage.this, DisplayOrders.class);
                startActivity(i);
            }
        });

        // adding on click listener for button
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                Intent i = new Intent(CompanyPage.this, RegisterDate.class);
                startActivity(i);            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==(R.id.logout)){
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,Main_page.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}