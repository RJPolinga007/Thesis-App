package com.bisu.herbalplantidentification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class UpdateDetails extends AppCompatActivity {

    EditText usernameET, emailET, passwordET, editUsernameET, editEmailET, editPasswordET;
    TextView idTV;
    Button updateBTN;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        idTV = findViewById(R.id.idTV);
        usernameET = findViewById(R.id.usernameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);

        editUsernameET = findViewById(R.id.editUsernameET);
        editEmailET = findViewById(R.id.editEmailET);
        editPasswordET = findViewById(R.id.editPasswordET);

        updateBTN = (Button) findViewById(R.id.updateBTN);


        idTV.setText( "" + SharedPrefManager.getInstance(this).getId());
        usernameET.setText(SharedPrefManager.getInstance(this).getUsername());
        emailET.setText(SharedPrefManager.getInstance(this).getEmail());
        passwordET.setText("cannot be showm for security purposes");

        editUsernameET.setText(SharedPrefManager.getInstance(this).getUsername());
        editEmailET.setText(SharedPrefManager.getInstance(this).getEmail());

        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

    }

    private void updateInfo() {
        int id = SharedPrefManager.getInstance(this).getId();
        String username = editUsernameET.getText().toString().trim();
        String email = editEmailET.getText().toString().trim();
        String password = editPasswordET.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_INFO_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")){
                                //success
                                //display success message
                                Toast.makeText(UpdateDetails.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                        id,
                                        username,
                                        email,
                                        password
                                );
                                startActivity(new Intent(getApplicationContext(), UpdateDetails.class));
                                finish();
                            }else{
                                //error
                                //display error message
                                Toast.makeText(UpdateDetails.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateDetails.this, "Connection Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", String.valueOf(id));
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void toHome(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}