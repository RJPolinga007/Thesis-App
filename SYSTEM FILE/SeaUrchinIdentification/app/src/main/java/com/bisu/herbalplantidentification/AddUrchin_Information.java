package com.bisu.herbalplantidentification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class AddUrchin_Information extends AppCompatActivity {

    private EditText name_urchinET, local_nameET, scientific_nameET, descriptionET, statusET;

    private Button addInformationBTN;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_urchin_information);

        name_urchinET = (EditText) findViewById(R.id.name_urchinET);
        local_nameET = (EditText) findViewById(R.id.local_nameET);
        scientific_nameET = (EditText) findViewById(R.id.scientific_nameET);
        descriptionET = (EditText) findViewById(R.id.descriptionET);
        statusET = (EditText) findViewById(R.id.statusET);

        addInformationBTN = (Button) findViewById(R.id.addInformationBTN);
        progressDialog = new ProgressDialog(this);
        addInformationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_NewInformation();
            }
        });
    }

    private void add_NewInformation() {
        final String name_urchin = name_urchinET.getText().toString().trim();
        final String local_name = local_nameET.getText().toString().trim();
        final String scientific_name = scientific_nameET.getText().toString().trim();
        final String description_urchin = descriptionET.getText().toString().trim();
        final String status = statusET.getText().toString().trim();

        progressDialog.setMessage("Adding Information....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_CREATE_INFORMATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name_urchin",name_urchin);
                params.put("local_name",local_name);
                params.put("scientific_name",scientific_name);
                params.put("description_urchin",description_urchin);
                params.put("status",status);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void clickToBack(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void clickToListAdd(View view) {
        startActivity(new Intent(getApplicationContext(), AddUrchin_Information.class));
    }


}