package com.example.recycleit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Previousorders extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    ArrayList<MyModel> list;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previousrders);
        auth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        rv = findViewById(R.id.rv);
        reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("UserData").orderByChild("number").equalTo((auth.getCurrentUser().getPhoneNumber()).substring(3));
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MyModel myModel = dataSnapshot.getValue(MyModel.class);
                        list.add(myModel);
                    }
                    MyAdapter adapter = new MyAdapter(Previousorders.this, list);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(Previousorders.this));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Previousorders.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            });
    }
}