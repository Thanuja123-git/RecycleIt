package com.example.recycleit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.recycleit.databinding.ActivityDisplayOrdersBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayOrders extends AppCompatActivity {
    RecyclerView rv;
    DatabaseReference reference;
    ArrayList<MyModel> list;
    ActivityDisplayOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display_orders);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_display_orders);
        rv = findViewById(R.id.rv);
        list = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference();

    }

    public void search(View view) {
        Query query = reference.child("UserData").orderByChild("code").equalTo(binding.locale.getText().toString().toLowerCase());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MyModel myModel = dataSnapshot.getValue(MyModel.class);
                    list.add(myModel);
                }
                MyAdapter adapter = new MyAdapter(DisplayOrders.this,list);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(DisplayOrders.this));

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(DisplayOrders.this, ""+error,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}