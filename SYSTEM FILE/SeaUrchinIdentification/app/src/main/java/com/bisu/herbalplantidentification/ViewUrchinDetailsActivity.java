package com.bisu.herbalplantidentification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class ViewUrchinDetailsActivity extends AppCompatActivity {

    TextView urchinNameTV, scientific_nameTV,local_nameTV, descriptionTV, statusTV, idTV;
    ProgressDialog progressDialog;
    String urchin_id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_urchin_details);

        progressDialog = new ProgressDialog(this);

        urchinNameTV = findViewById(R.id.urchinNameTV);
        local_nameTV = findViewById(R.id.local_nameTV);
        scientific_nameTV = findViewById(R.id.scientific_nameTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        statusTV = findViewById(R.id.statusTV);
        idTV = findViewById(R.id.idTV);


        // Retrieve data from Intent
        Intent intent = getIntent();
        urchin_id = intent.getStringExtra("urchin_id");
        String name = intent.getStringExtra("name_urchin");
        String scientificName = intent.getStringExtra("scientific_name");
        String localName = intent.getStringExtra("local_name");
        String description = intent.getStringExtra("description_urchin");
        String status = intent.getStringExtra("status");

        // Set data to TextViews
        urchinNameTV.setText(name);
        scientific_nameTV.setText(scientificName);
        local_nameTV.setText(localName);
        descriptionTV.setText(description);
        statusTV.setText(status);
        idTV.setText("ID: " + urchin_id);
    }



    //back to previous activity
    public void clickToBack(View view) {
        startActivity(new Intent(getApplicationContext(), viewlist_urchin.class));
        finish();
    }

    public void deleteUrchinOnClick(View view) {
        deleteUrchin();
    }

    private void deleteUrchin() {
        progressDialog.setMessage("Deleting urchin details...");
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequestDeleteUrchin = new StringRequest(Request.Method.POST, Constants.URL_DELETE_URCHIN_ITEM,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    if(!jsonObject.getBoolean("error")){
                                        Toast.makeText(ViewUrchinDetailsActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), viewlist_urchin.class));
                                        finish();
                                    }else{
                                        Toast.makeText(ViewUrchinDetailsActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(ViewUrchinDetailsActivity.this, "Some error occured: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", urchin_id);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ViewUrchinDetailsActivity.this);
                requestQueue.add(stringRequestDeleteUrchin);
            }
        }, 1000);
    }
}