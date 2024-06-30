package com.bisu.herbalplantidentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class addplantdescription extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;
    EditText Llatitude,Llongitude;
    Button submit,locBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplantdescription);

        EditText date = (EditText) findViewById(R.id.date);
        Llatitude = (EditText) findViewById(R.id.latitude);
        Llongitude = (EditText) findViewById(R.id.longitude);
        TextView back = (TextView) findViewById(R.id.backBtn);
        submit = (Button) findViewById(R.id.addImage);
        locBtn = (Button) findViewById(R.id.map);
        Spinner name = (Spinner) findViewById(R.id.dynamic_spinner);
        Spinner health = (Spinner) findViewById(R.id.dynamic_spinner2);
        Spinner density = (Spinner) findViewById(R.id.dynamic_spinner3);
        TextView icon1 = findViewById(R.id.registration);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce_up);
        icon1.startAnimation(animation);

        ArrayList<String> nameList = new ArrayList<String>(Arrays.asList("Achoite", "Asunting", "Atay-atay", "Atis", "Ampalaya", "Boongon", "Dahong Mariya", "Guyabano", "Dahong Bayabas", "Hanlilika"
                ,"Insulin", "Kalabo", "Kamunggay", "Lubi-lubi", "Luy.a", "Lemoncito", "Manjana", "Samin-samin", "Tawa-tawa", "Tuba-tuba", "Mangga", "Nangka", "Hagonoy"));
        ArrayList<String> healthList = new ArrayList<String>(Arrays.asList("Healthy", "Not Too Healthy", "Very Healthy"));
        ArrayList<String> densityList = new ArrayList<String>(Arrays.asList("Many", "Not Too Many", "Too Many", "Thick", "Not Too Thick", "Too Thick"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(addplantdescription.this,
                android.R.layout.simple_spinner_dropdown_item, nameList);
        name.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(addplantdescription.this,
                android.R.layout.simple_spinner_dropdown_item, healthList);
        health.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(addplantdescription.this,
                android.R.layout.simple_spinner_dropdown_item, densityList);
        density.setAdapter(adapter3);

        String dateToday = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        date.setText(dateToday);

        ActivityCompat.requestPermissions( this,
                new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        setLocation();


        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Llatitude.getText().toString().matches("")){
                    Toast.makeText(addplantdescription.this, "Input latitude", Toast.LENGTH_SHORT).show();
                }
                else if(Llongitude.getText().toString().matches("")){
                    Toast.makeText(addplantdescription.this, "Input longitude", Toast.LENGTH_SHORT).show();
                }
                else{
                    submit.setEnabled(false);
                    submit.setText("Adding...");

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", name.getSelectedItem().toString());
                    map.put("date", date.getText().toString());
                    map.put("density", density.getSelectedItem().toString());
                    map.put("health", health.getSelectedItem().toString());
                    map.put("longitude", Llongitude.getText().toString());
                    map.put("latitude", Llatitude.getText().toString());

                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

                    mDatabase.child("plants").orderByChild("name").equalTo(name.getSelectedItem().toString())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        for (DataSnapshot c: snapshot.getChildren()){
                                            if(c.child("density").getValue().toString().matches(density.getSelectedItem().toString())
                                                    && c.child("health").getValue().toString().matches(health.getSelectedItem().toString())
                                                    && c.child("longitude").getValue().toString().matches(Llongitude.getText().toString())
                                                    && c.child("latitude").getValue().toString().matches(Llatitude.getText().toString()))
                                            {
                                                Toast.makeText(addplantdescription.this, "Plant already exist", Toast.LENGTH_SHORT).show();
                                                submit.setEnabled(true);
                                                submit.setText("ADD PLANT");
                                            }
                                            else{
                                                mDatabase.child("plants").push().setValue(map)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    submit.setEnabled(true);
                                                                    submit.setText("ADD PLANT");
                                                                    Toast.makeText(addplantdescription.this, "Record Added", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {
                                                                    submit.setEnabled(true);
                                                                    submit.setText("ADD PLANT");
                                                                    Toast.makeText(addplantdescription.this, "Failed to add", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    }
                                    else{
                                        mDatabase.child("plants").push().setValue(map)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            submit.setEnabled(true);
                                                            submit.setText("ADD PLANT");
                                                            Toast.makeText(addplantdescription.this, "Record Added", Toast.LENGTH_SHORT).show();
                                                        }
                                                        else {
                                                            submit.setEnabled(true);
                                                            submit.setText("ADD PLANT");
                                                            Toast.makeText(addplantdescription.this, "Failed to add", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
            }
        });

    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Llongitude.setText(longitude);
                Llatitude.setText(latitude);
                submit.setEnabled(true);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setLocation(){
        locBtn.setText("GENERATING GPS...");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
            locBtn.setText("GENERATE GPS");
            Toast.makeText(this, "Location Set", Toast.LENGTH_SHORT).show();
        }
    }
}