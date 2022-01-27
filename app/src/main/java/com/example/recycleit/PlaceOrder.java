package com.example.recycleit;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.recycleit.databinding.ActivityPlaceOrderBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class PlaceOrder extends AppCompatActivity {
    TextView receiver_msg;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ActivityPlaceOrderBinding binding;
    Uri uri;
    TextView tv;
    String locality;
    String code;
    String address;
    FusedLocationProviderClient locationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* setContentView(R.layout.activity_main);*/


        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_place_order);
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");
        storageReference = FirebaseStorage.getInstance().getReference()
                .child("Images/"+ UUID.randomUUID().toString());
        tv = findViewById(R.id.textView);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        ActivityResultLauncher<String> mgetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                uri = result;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    binding.iv.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mgetContent.launch("image/*");
            }
        });
    }
    public void getDeviceLocationDetail(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location=task.getResult();
                Geocoder geocoder=new Geocoder(PlaceOrder.this, Locale.getDefault());
                try {
                    List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    locality=String.valueOf(addresses.get(0).getLocality());
                    code=String.valueOf(addresses.get(0).getPostalCode());
                    String address=String.valueOf(addresses.get(0).getAddressLine(0));
                    String postalCode=String.valueOf(addresses.get(0).getPostalCode());
                    tv.setText("Locality :"+locality+"\n"+"Address :"+address+"\n"+"Postal Code :"+postalCode);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void register(View view) {
        if(binding.iv.getDrawable()==null||binding.name.getText().toString().isEmpty()||binding.number.getText().toString().isEmpty()||binding.plastic.getText().toString().isEmpty()){
            Toast.makeText(PlaceOrder.this, "Please fill the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            dataregister();
        }


    }
    public void set(View view){
        receiver_msg = (TextView)findViewById(R.id.textView);

        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        locality = intent.getStringExtra("message_key");
        address = intent.getStringExtra("message_key1");
        code = intent.getStringExtra("message_key2");
        String str3 = "Locality :"+locality+"\n"+"Address :"+address+"\n"+"Postal Code:"+code;
        tv.setText("Locality :"+locality+"\n"+"Address :"+address+"\n"+"Postal Code:"+code);

        // display the string into textView
        receiver_msg.setText(str3);

    }
    public void dataregister(){
        storageReference.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        String name = binding.name.getText().toString();
                        String plastic = binding.plastic.getText().toString();
                        String num = binding.number.getText().toString();
                        String locale = locality.toString().toLowerCase();
                        String pcode = code.toString();
                        String textview = binding.textView.getText().toString();
                        MyModel myModel = new MyModel(url, name, plastic, num, locale, pcode, textview);

                        /*  String id = databaseReference.push().getKey();*/
                        databaseReference.child(num).setValue(myModel);
                        Toast.makeText(PlaceOrder.this, "Data Inserted",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PlaceOrder.this, SuccessfulOrder.class));
                        finish();

                    }
                });
            }
        });

    }


}