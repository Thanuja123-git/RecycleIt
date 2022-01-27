package com.example.recycleit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Locationfrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationfrag);
    }

    public void set(View view) {
        startActivity(new Intent(this,Location.class));
    }

    public void current(View view) {
        startActivity(new Intent(this,PlaceOrder.class));
    }
}