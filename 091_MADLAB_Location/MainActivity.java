package com.example.myapplication;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         Geocoder coder = new Geocoder(this);
        Button getLocation = findViewById(R.id.getLocation);

         TextView latView = findViewById(R.id.locationLat);
         TextView longView = findViewById(R.id.locationLong);


        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);


        client = LocationServices.getFusedLocationProviderClient(this);


        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    client.getLastLocation().addOnSuccessListener(MainActivity.this, location -> {
                        Toast.makeText(MainActivity.this,""+location.toString(),Toast.LENGTH_SHORT);

                        if (location != null) {
                            Double lng =location.getLongitude();
                            Double lat = location.getLatitude();
                            latView.setText(lat.toString());
                            longView.setText(lng.toString());
                        }
                    });
                }
                catch (SecurityException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
