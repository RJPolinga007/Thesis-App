package com.bisu.herbalplantidentification;

import com.bisu.herbalplantidentification.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button loginBTN;
    EditText usernameET, passwordET;

    ProgressDialog progressDialog;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        loginBTN = findViewById(R.id.loginBTN);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging in...");

        // Initially disable login button
        loginBTN.setEnabled(false);

        // TextWatcher to enable/disable login button based on input fields
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Enable login button only if both fields are not empty
                boolean enableButton = !TextUtils.isEmpty(usernameET.getText()) && !TextUtils.isEmpty(passwordET.getText());
                loginBTN.setEnabled(enableButton);
            }
        };

        // Add textWatcher to EditText fields
        usernameET.addTextChangedListener(textWatcher);
        passwordET.addTextChangedListener(textWatcher);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAuth();
            }
        });

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return;
        }


        if (SharedPrefAdminManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
            return;
        }
    }

    private void userAuth(){
        final String username = usernameET.getText().toString().trim();
        final String password = passwordET.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")){

                                int role = jsonObject.getInt("role");

                                if (role == 1){
                                    Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                            jsonObject.getInt("id"),
                                            jsonObject.getString("username"),
                                            jsonObject.getString("email"),
                                            jsonObject.getString("password")
                                    );
//                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                    finish();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else if (role == 0){ //admin
                                    Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    SharedPrefAdminManager.getInstance(getApplicationContext()).userLogin(
                                            jsonObject.getInt("id"),
                                            jsonObject.getString("username"),
                                            jsonObject.getString("email"),
                                            jsonObject.getString("password")
                                    );
                                    startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
                                    finish();
                                }

                            }
                            else{
                                Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Some error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Connection error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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

    public void clickToRegister(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

}