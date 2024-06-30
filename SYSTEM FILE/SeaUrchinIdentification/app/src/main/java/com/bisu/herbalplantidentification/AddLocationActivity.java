package com.bisu.herbalplantidentification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText addressET, latitudeET, longitudeET;
    Button getGpsBTN, verifyGpsBTN, insertGpsBTN;
    private ProgressDialog progressDialog;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private static final int DESIRED_LOCATION_ACCURACY = LocationRequest.PRIORITY_HIGH_ACCURACY;
    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap gMap;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        addressET = findViewById(R.id.addressET);
        latitudeET = findViewById(R.id.latitudeET);
        longitudeET = findViewById(R.id.longitudeET);

        getGpsBTN = findViewById(R.id.getGpsBTN);
        verifyGpsBTN = findViewById(R.id.verifyGpsBTN);
        insertGpsBTN = findViewById(R.id.insertGpsBTN);

        progressDialog = new ProgressDialog(this);



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getGpsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });

        verifyGpsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:" + latitudeET + "," + longitudeET.getText().toString() + "?z=90&q=" + latitudeET.getText().toString() + "," + longitudeET.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


        insertGpsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertGpsToDB();
            }
        });
    }

    private void insertGpsToDB() {
        final String address = addressET.getText().toString();
        final String latitude = latitudeET.getText().toString();
        final String longitude = longitudeET.getText().toString();

        progressDialog.setMessage("Inserting Location...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_INSERT_GPS_LOCATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //code for handling response

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("address", address);
                params.put("latitude", latitude);
                params.put("longitude", longitude);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(DESIRED_LOCATION_ACCURACY);

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;

                    mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(AddLocationActivity.this);

                    Double latitude = currentLocation.getLatitude();
                    Double longitude = currentLocation.getLongitude();

                    Geocoder geocoder = new Geocoder(AddLocationActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        String address = addresses.get(0).getAddressLine(0);
                        addressET.setText(address);
                        latitudeET.setText(String.valueOf(latitude));
                        longitudeET.setText(String.valueOf(longitude));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == FINE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{
                Toast.makeText(this, "Location permission is denied! FloodReady won't work well", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void toHomeAdmin(View view) {
        startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
        finish();
    }


    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
        finish();
    }

//    ---------------------------------------------------------------------


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.getUiSettings().setCompassEnabled(true);

        getUrchinLocation();

        LatLng sydney = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        gMap.addMarker(new MarkerOptions().position(sydney).title("You are here!"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0f));

    }

    private void getUrchinLocation() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_GET_SEA_URCHIN_LOCATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")){
                                JSONArray seaUrchinLocation = jsonObject.getJSONArray("SeaUrchinLocations");

                                for (int i = 0; i < seaUrchinLocation.length(); i++){
                                    JSONObject locObject = seaUrchinLocation.getJSONObject(i);

                                    double latitude = locObject.getDouble("latitude");
                                    double longitude = locObject.getDouble("longitude");

                                    gMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(latitude, longitude))
                                            .title("Urchin Rich Location"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddLocationActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



}