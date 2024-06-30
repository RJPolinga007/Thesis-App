package com.bisu.herbalplantidentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminHomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private List<HashMap<String, String>> data;
    View opacity;
    CardView accountCardView;
    Button logoutBTN;
    ListView simpleList;
    TextView count_adminTV, count_usersTV;
    ProgressDialog progressDialog;

    private GoogleMap gMap;
    private SupportMapFragment mapFragment;
    private final int FINE_PERMISSION_CODE = 1;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private static final int DESIRED_LOCATION_ACCURACY = LocationRequest.PRIORITY_HIGH_ACCURACY;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        progressDialog = new ProgressDialog(this);

        simpleList = findViewById(R.id.lview);

        //Instantiate Area
        opacity = findViewById(R.id.opacity);
        accountCardView = findViewById(R.id.accountCardView);
        logoutBTN = findViewById(R.id.logoutBTN);
        TextView email = findViewById(R.id.email);
        TextView username = findViewById(R.id.username);


        count_adminTV = findViewById(R.id.count_adminTV);
        count_usersTV = findViewById(R.id.count_usersTV);

        progressDialog = new ProgressDialog(this);

        fetchCounts();

        //Instantiate Area



        if(!SharedPrefAdminManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        username.setText(SharedPrefAdminManager.getInstance(this).getUsername());
        email.setText(SharedPrefAdminManager.getInstance(this).getEmail());

        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefAdminManager.getInstance(getApplicationContext()).logout();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLastLocation();
    }

    private void fetchCounts() {
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_FETCH_COUNTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (!jsonObject.getBoolean("error")) {
                                int countAdmin = jsonObject.getInt("count_users_role_one");
                                int countUsers = jsonObject.getInt("count_users_role_zero");

                                // Update TextViews
                                count_adminTV.setText(String.valueOf(countAdmin));
                                count_usersTV.setText(String.valueOf(countUsers));

                            } else {
                                Toast.makeText(AdminHomeActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AdminHomeActivity.this, "JSON Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AdminHomeActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


//    public void getCountRoles(){
//
//        count_userTV.setText(count_userTV);
//        count_adminTV.setText(count_adminTV);
//
//    }

    public void clickToToggleAccount(View view) {

        if (opacity.getVisibility() == View.VISIBLE) {
            opacity.setVisibility(View.GONE);
            accountCardView.setVisibility(View.GONE);
        } else {
            opacity.setVisibility(View.VISIBLE);
            accountCardView.setVisibility(View.VISIBLE);
        }

    }

    public void clickToCloseCardAccount(View view) {
        if (opacity.getVisibility() == View.VISIBLE) {
            opacity.setVisibility(View.GONE);
            accountCardView.setVisibility(View.GONE);
        } else {
            opacity.setVisibility(View.VISIBLE);
            accountCardView.setVisibility(View.VISIBLE);
        }
    }

    public void clickToAddlocation(View view) {
        startActivity(new Intent(getApplicationContext(), AddLocationActivity.class));
        finish();
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        // Create the alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");

        // Set the positive button (OK button) and its click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Finish the activity
                finish();
                // Add any additional transitions or logic here
            }
        });

        // Set the negative button (Cancel button) and its click listener
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and stay in the activity
                dialog.dismiss();
            }
        });

        // Create the alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the alert dialog
        alertDialog.show();
    }

    public void clickToRegisterAdmin(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterAdminActivity.class));
        finish();
    }

    public void clickToListAdd(View view) {
        startActivity(new Intent(getApplicationContext(), Admin_AddUrchin_Information.class));
    }

    public void clickToAccountSettings(View view) {
        startActivity(new Intent(getApplicationContext(), UpdateAdminDetails.class));
    }

    public void clickToBrowse(View view) {
        startActivity(new Intent(getApplicationContext(), Browse_Info.class));
    }

//    ----------------------------------------------------------------------------------------

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
                        Toast.makeText(AdminHomeActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null){
                    currentLocation = location;

                    mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(AdminHomeActivity.this);
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
                Toast.makeText(this, "Location permission is denied! The app won't work well", Toast.LENGTH_SHORT).show();
            }
        }
    }


}